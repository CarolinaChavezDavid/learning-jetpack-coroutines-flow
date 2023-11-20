package com.carolina.myapplication.usecases.flow.usecase2

import android.os.Bundle
import androidx.activity.viewModels
import com.carolina.myapplication.base.BaseActivity
import com.carolina.myapplication.base.flowUseCase2Description
import com.carolina.myapplication.databinding.ActivityFlowUsecase1Binding
import com.carolina.myapplication.utils.setGone
import com.carolina.myapplication.utils.setVisible
import com.carolina.myapplication.utils.toast
import kotlinx.coroutines.Dispatchers
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat

class FlowUseCase2Activity : BaseActivity() {

    private val binding by lazy { ActivityFlowUsecase1Binding.inflate(layoutInflater) }
    private val adapter = StockAdapter()

    private val viewModel: FlowUseCase2ViewModel by viewModels {
        ViewModelFactory(
            NetworkStockPriceDataSource(mockApi(applicationContext)),
            Dispatchers.Default,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter

        viewModel.currentStockPriceAsLiveData.observe(this) { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        }
    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                binding.progressBar.setVisible()
                binding.recyclerView.setGone()
            }
            is UiState.Success -> {
                binding.recyclerView.setVisible()
                binding.lastUpdateTime.text =
                    "lastUpdateTime: ${LocalDateTime.now().toString(DateTimeFormat.fullTime())}"
                adapter.stockList = uiState.stockList
                binding.progressBar.setGone()
            }
            is UiState.Error -> {
                toast(uiState.message)
                binding.progressBar.setGone()
            }
        }
    }

    override fun getToolbarTitle() = flowUseCase2Description
}
