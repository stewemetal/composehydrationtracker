package hu.stewemetal.composehydrationtracker.ui.main.stats

import android.view.ViewGroup.LayoutParams
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
import com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM_INSIDE
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import hu.stewemetal.composehydrationtracker.R.string

@Composable
fun StatsScreen() {

    val viewModel = hiltViewModel<StatsViewModel>()

    val uiState = viewModel.uiState
    val stats = uiState.stats

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (stats == null || stats.isEmpty()) {
            Text(stringResource(string.stats_empty))
        } else {
            AndroidView(
                factory = { context ->
                    BarChart(context).apply {
                        layoutParams = LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT,
                        )
                    }
                },
                update = {
                    val xAxisValues = stats.map { it.dateTime.toString() }
                    val entries = stats.mapIndexed { index, value ->
                        BarEntry(
                            index.toFloat(),
                            value.milliliters.toFloat(),
                        )
                    }
                    val barDataSet = BarDataSet(
                        entries,
                        "Intake",
                    )
                    it.xAxis.apply {
                        valueFormatter = IndexAxisValueFormatter(xAxisValues)
                        labelCount = entries.size
                        this.position = BOTTOM
                    }
                    it.data = BarData(barDataSet)
                    it.invalidate()
                }
            )
        }
    }
}