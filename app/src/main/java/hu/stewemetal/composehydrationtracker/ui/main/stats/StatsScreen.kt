package hu.stewemetal.composehydrationtracker.ui.main.stats

import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import hu.stewemetal.composehydrationtracker.R.string
import hu.stewemetal.composehydrationtracker.domain.model.ConsumptionPerDay
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatsScreen(
    viewModel: StatsViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState
    val stats = uiState.stats

    StatsContent(stats = stats)
}

@Composable
fun StatsContent(stats: List<ConsumptionPerDay>?) {
    if (stats.isNullOrEmpty()) {
        EmptyStats()
    } else {
        StatsChart(stats = stats) {}
    }
}

@Composable
fun EmptyStats() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(stringResource(string.stats_empty))
    }
}

@Composable
fun StatsChart(stats: List<ConsumptionPerDay>, onButtonClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            modifier = Modifier.semantics {
                testTag = "stats_chart"
            },
            factory = { context ->
                BarChart(context).apply {
                    layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT,
                    )
                }
            },
            update = { barChart ->
                val xAxisValues = stats.map { it.date.toString() }
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
                barChart.xAxis.apply {
                    valueFormatter = IndexAxisValueFormatter(xAxisValues)
                    labelCount = entries.size
                    this.position = BOTTOM
                }
                barChart.data = BarData(barDataSet)
                barChart.invalidate()
            }
        )
        AndroidView(factory = { context ->
            Button(context).apply {
                text = "Demo Button"
                layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                setOnClickListener {
                    onButtonClick()
                }
            }
        })
    }
}
