package com.cs407.badgerbudgetbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart.dragDecelerationFrictionCoef = 0.95f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.setDrawCenterText(true)
        pieChart.rotationAngle = 0f
        pieChart.isRotationEnabled = true
        pieChart.isHighlightPerTapEnabled = true
        pieChart.animateY(1400, Easing.EaseInOutQuad)
        pieChart.legend.isEnabled = true
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setEntryLabelTextSize(12f)

        val bv = ViewModelProvider(this).get(BudgetViewModel::class.java)
        val transactionArray = bv.getTransactionTotals()
        val entries: ArrayList<PieEntry> = ArrayList()

        // Observing transaction data
        transactionArray.observe(viewLifecycleOwner, Observer { list ->
            if (list != null && list.isNotEmpty()) {
                val categories = listOf("Food", "School Supplies", "Recreation", "Rent", "Tuition", "Clothing", "Emergency", "Other") // Example categories
                val quantityArray = ArrayList(list)

                for ((index, value) in quantityArray.withIndex()) {
                    // Ensure the index does not exceed the categories list size
                    val label = if (index < categories.size) categories[index] else "Other"
                    entries.add(PieEntry(value, label))
                }

                // Create the dataset and update the chart
                val dataSet = PieDataSet(entries, "")
                dataSet.setDrawIcons(false)
                dataSet.sliceSpace = 3f
                dataSet.iconsOffset = MPPointF(0f, 40f)
                dataSet.selectionShift = 5f

                // Set colors
                val colors = listOf(
                    resources.getColor(R.color.button_green),
                    resources.getColor(R.color.button_blue),
                    resources.getColor(R.color.red),
                    resources.getColor(R.color.purple),
                    resources.getColor(R.color.orange),
                    resources.getColor(R.color.seafoam),
                    resources.getColor(R.color.red),
                    resources.getColor(R.color.teal_200)
                )

                dataSet.colors = colors

                val data = PieData(dataSet)
                data.setValueFormatter(PercentFormatter())
                data.setValueTextSize(15f)
                data.setValueTypeface(Typeface.DEFAULT_BOLD)
                data.setValueTextColor(Color.BLACK)

                pieChart.data = data
                pieChart.highlightValues(null)
                pieChart.invalidate()
            }
        })
    }
}
