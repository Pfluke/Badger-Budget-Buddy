package com.cs407.badgerbudgetbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.graphics.Color
import android.graphics.Typeface
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cs407.badgerbudgetbuddy.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF

class HomeFragment : Fragment() {

    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment layout
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize UI elements
        val switchAccountButton: Button = view.findViewById(R.id.switchAcct)
        val viewReceiptsButton: Button = view.findViewById(R.id.viewReceipt)
        val viewRecentTransacButton: Button = view.findViewById(R.id.viewRecentTransac)

        // Set up navigation actions
        val navController = findNavController()
        switchAccountButton.setOnClickListener {
            navController.navigate(R.id.action_home_to_switchAccount)
        }

        viewReceiptsButton.setOnClickListener {
            navController.navigate(R.id.action_home_to_viewReceipts)
        }

        viewRecentTransacButton.setOnClickListener {
            navController.navigate(R.id.action_home_to_viewRecentTransactions)
        }

        // Initialize PieChart and other UI setup
        pieChart = view.findViewById(R.id.pieChart)
        setupPieChart()

        return view
    }

    private fun setupPieChart() {
        // Setting up the PieChart (similar to your original code)
        pieChart.setUsePercentValues(true)
        pieChart.getDescription().setEnabled(false)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart.setDragDecelerationFrictionCoef(0.95f)
        pieChart.setDrawHoleEnabled(true)
        pieChart.setHoleColor(Color.WHITE)
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)
        pieChart.setHoleRadius(58f)
        pieChart.setTransparentCircleRadius(61f)
        pieChart.setDrawCenterText(true)
        pieChart.setRotationAngle(0f)
        pieChart.setRotationEnabled(true)
        pieChart.setHighlightPerTapEnabled(true)
        pieChart.animateY(1400, Easing.EaseInOutQuad)
        pieChart.legend.isEnabled = true
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        // Create data entries for the pie chart
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(40f))
        entries.add(PieEntry(20f))
        entries.add(PieEntry(10f))
        entries.add(PieEntry(30f))

        val dataSet = PieDataSet(entries, "Food")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // Set colors
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.purple_200))
        colors.add(resources.getColor(R.color.yellow))
        colors.add(resources.getColor(R.color.red))
        colors.add(resources.getColor(R.color.teal_200))

        dataSet.colors = colors

        // Create and set the PieData
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.WHITE)
        pieChart.setData(data)

        // Undo highlights
        pieChart.highlightValues(null)

        // Refresh the chart
        pieChart.invalidate()
    }
}
