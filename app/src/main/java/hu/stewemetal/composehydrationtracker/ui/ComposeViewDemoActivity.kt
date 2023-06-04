package hu.stewemetal.composehydrationtracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnDetachedFromWindow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import hu.stewemetal.composehydrationtracker.databinding.ActivityComposeViewDemoBinding
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme
import hu.stewemetal.composehydrationtracker.ui.view.CustomButton

class ComposeViewDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComposeViewDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComposeViewDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "ComposeView Demo Title"
        binding.composeView.apply {

            setViewCompositionStrategy(DisposeOnDetachedFromWindow)
            setContent {
                HydrationTrackerTheme {
                    CustomButton(
                        text = "Demo Button",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .testTag("demo_custom_button"),
                    ) {}
                }
            }
        }
    }
}
