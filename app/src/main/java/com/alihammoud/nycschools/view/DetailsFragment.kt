package com.alihammoud.nycschools.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback


class DetailsFragment : Fragment() , OnMapReadyCallback  {

    private lateinit var sharedViewModel: SharedViewModel

    lateinit var pieListGrad: ArrayList<PieEntry>
    lateinit var pieListAtten: ArrayList<PieEntry>
    lateinit var dataSetGrad: PieDataSet
    lateinit var dataSetAtten: PieDataSet
    lateinit var dataPieGrad: PieData
    lateinit var dataPieAtten: PieData
    lateinit var schoolID: String
    var atten_rate: Float = 0f
    var grad_rate: Float = 0f


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailsBinding.inflate(layoutInflater)
        val manager = (context as FragmentActivity).supportFragmentManager
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

        schoolID = args?.get("id").toString()
        atten_rate = args?.get("atten").toString().toFloat()*100
        grad_rate = args?.get("grad").toString().toFloat()*100

        fillPies()

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        context?.let { sharedViewModel.getDetails(it) }


        sharedViewModel._satData.observe(viewLifecycleOwner, Observer{
            Log.e("RECEIVED DETAILS FRAG", it.toString())

            val data = it.toList()
            for (i in data) {
                if (i.id == schoolID) {

                        binding.mathAvg.text = i.math_avg
                        binding.writingAvg.text = i.writing_avg
                        binding.readingAvg.text = i.reading_avg

                    binding.testTakers.text = i.takers_num

                }
            }
            binding.title.text = args?.get("name").toString()
            binding.desc.text = args?.get("desc").toString()
            binding.studentsNum.text = args?.get("studentNum").toString()
            binding.phone.text = "Phone Number: "+args?.get("phone").toString()
            binding.address.text = "Address: "+args?.get("address").toString()

        } )


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

        binding.back.setOnClickListener(
            View.OnClickListener {
               manager.popBackStack()
            }
        )

        return  binding.root
    }

    private fun fillPies()
    {
        pieListGrad = ArrayList()
        pieListAtten = ArrayList()


        pieListGrad.add(PieEntry(grad_rate, "Graduation Rate"))
        pieListGrad.add(PieEntry(100 - grad_rate, "Drop Rate"))
        pieListAtten.add(PieEntry(atten_rate, "Attendance Rate"))
        pieListAtten.add(PieEntry(100 - atten_rate, "Absence Rate"))

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

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

}