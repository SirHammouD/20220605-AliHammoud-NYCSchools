package com.alihammoud.nycschools.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alihammoud.nycschools.databinding.FragmentDetailsBinding
import com.alihammoud.nycschools.viewmodel.SharedViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback


class DetailsFragment : Fragment() , OnMapReadyCallback  {

    // Declare the ViewModel, along with the UI handlers to be used
    private lateinit var sharedViewModel: SharedViewModel

    // Declare variables needed for data manipulation in the graphs
    lateinit var pieListGrad: ArrayList<PieEntry>
    lateinit var pieListAtten: ArrayList<PieEntry>
    lateinit var dataSetGrad: PieDataSet
    lateinit var dataSetAtten: PieDataSet
    lateinit var dataPieGrad: PieData
    lateinit var dataPieAtten: PieData
    lateinit var schoolID: String
    var attenRate: Float = 0f
    var gradRate: Float = 0f


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment using binding
        val binding = FragmentDetailsBinding.inflate(layoutInflater)

        // Start the Fragment manager for future transaction
        val manager = (context as FragmentActivity).supportFragmentManager
        // Initialize arguments to hold bundles
        val args = this.arguments

        /* var mapViewBundle: Bundle = Bundle()
        if (savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle("Key")!!
        }

        binding.mapView.apply {
            onCreate(mapViewBundle)
            getMapAsync {
            }
        }*/

        // Get Arguments from the bundle and call each value by its ID then assign to the corresponding variable
        schoolID = args?.get("id").toString()
        attenRate = args?.get("atten").toString().toFloat()*100
        gradRate = args?.get("grad").toString().toFloat()*100

        // Get the Pies Data
        fillPies()
        // Assign the ViewModel
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        // Call the getData from the ViewModel to fetch data
        context?.let { sharedViewModel.getDetails(it) }

        // Setup an observer to observe for changes in our data and update the recyclerview accordingly
        sharedViewModel._satData.observe(viewLifecycleOwner, Observer{
            Log.e("RECEIVED DETAILS FRAG", it.toString())

            // Turn the data obtained into a list, then filter through based on matching ID obtained from the previous fragment
            val data = it.toList()
            for (i in data) {
                // If ID matches then grab the data needed based on the model helpers
                if (i.id == schoolID) {
                // Assign data to the corresponding variable
                        binding.mathAvg.text = i.math_avg
                        binding.writingAvg.text = i.writing_avg
                        binding.readingAvg.text = i.reading_avg
                        binding.testTakers.text = i.takers_num
                }
            }
            // Get Arguments from the bundle and call each value by its ID then assign to the corresponding text view
            binding.title.text = args?.get("name").toString()
            binding.desc.text = args?.get("desc").toString()
            binding.studentsNum.text = args?.get("studentNum").toString()
            binding.phone.text = "Phone Number: "+args?.get("phone").toString()
            binding.address.text = "Address: "+args?.get("address").toString()

        } )


        // Set up the attributes of the Pie Charts
        binding.gradRate.apply {
            this.data = dataPieGrad

            description.text = ""
            setTouchEnabled(false)
            setExtraOffsets(0f, 0f, 0f, 0f)
            animateY(1400, Easing.EaseInOutQuad)
            setUsePercentValues(true)
            isRotationEnabled = false
            setDrawEntryLabels(false)
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.isWordWrapEnabled = true
            holeRadius = 50f
            transparentCircleRadius = 0f
            isDrawHoleEnabled = true
            setHoleColor(Color.WHITE)

            this.data.setValueTextSize(10f)
            this.data.setValueFormatter(PercentFormatter())
        }

        // Set up the attributes of the Pie Charts
        binding.attenRate.apply {
            this.data = dataPieAtten

            description.text = ""
            setTouchEnabled(false)
            setExtraOffsets(0f, 0f, 0f, 0f)
            animateY(1400, Easing.EaseInOutQuad)
            setUsePercentValues(true)
            isRotationEnabled = false
            setDrawEntryLabels(false)
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.isWordWrapEnabled = true
            holeRadius = 50f
            transparentCircleRadius = 0f
            isDrawHoleEnabled = true
            setHoleColor(Color.WHITE)

            this.data.setValueTextSize(10f)
            this.data.setValueFormatter(PercentFormatter())
            invalidate()
        }

        // Set on click listner for when the back btn in the toolbar click, popBackStack the Fragment that was saved in the Fragment Manager
        binding.back.setOnClickListener(
            View.OnClickListener {
               manager.popBackStack()
            }
        )

        return  binding.root
    }

    // A helper function used to to fill in the data into the Pie Charts
    private fun fillPies()
    {
        pieListGrad = ArrayList()
        pieListAtten = ArrayList()


        pieListGrad.add(PieEntry(gradRate, "Graduation Rate"))
        pieListGrad.add(PieEntry(100 - gradRate, "Drop Rate"))
        pieListAtten.add(PieEntry(attenRate, "Attendance Rate"))
        pieListAtten.add(PieEntry(100 - attenRate, "Absence Rate"))

        dataSetGrad = PieDataSet(pieListGrad, "")
        dataSetAtten = PieDataSet(pieListAtten, "")

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFFFFF"))

        dataSetGrad.sliceSpace = 0f
        dataSetGrad.colors = colors

        dataSetAtten.sliceSpace = 0f
        dataSetAtten.colors = colors

        dataPieGrad = PieData(dataSetGrad)
        dataPieAtten= PieData(dataSetAtten)
    }

    // Future Releases: in app Map for simplified location
    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

}