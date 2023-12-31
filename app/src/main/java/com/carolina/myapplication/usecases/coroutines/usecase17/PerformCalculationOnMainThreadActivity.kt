package com.carolina.myapplication.usecases.coroutines.usecase17

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.carolina.myapplication.base.BaseActivity
import com.carolina.myapplication.base.useCase17Description
import com.carolina.myapplication.databinding.ActivityCalculateonmainBinding
import com.carolina.myapplication.utils.setGone
import com.carolina.myapplication.utils.setVisible
import com.carolina.myapplication.utils.toast
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase17.PerformCalculationOnMainThreadViewModel
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase17.UiState

class PerformCalculationOnMainThreadActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase17Description

    private val binding by lazy { ActivityCalculateonmainBinding.inflate(layoutInflater) }
    private val viewModel: PerformCalculationOnMainThreadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.uiState().observe(
            this,
            Observer { uiState ->
                if (uiState != null) {
                    render(uiState)
                }
            },
        )
        binding.btnCalculateOnMain.setOnClickListener {
            val factorialOf = binding.editTextFactorialOf.text.toString().toIntOrNull()
            if (factorialOf != null) {
                viewModel.performCalculationOnMainThread(factorialOf)
            }
        }
        binding.btnCalculateOnMainUsingYield.setOnClickListener {
            val factorialOf = binding.editTextFactorialOf.text.toString().toIntOrNull()
            if (factorialOf != null) {
                viewModel.performCalculationOnMainThreadUsingYield(factorialOf)
            }
        }
        binding.btnCalculateWithDefaultDispatcher.setOnClickListener {
            val factorialOf = binding.editTextFactorialOf.text.toString().toIntOrNull()
            if (factorialOf != null) {
                viewModel.performCalculationWithDefaultDispatcher(factorialOf)
            }
        }
    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                onLoad()
            }
            is UiState.Success -> {
                onSuccess(uiState)
            }
            is UiState.Error -> {
                onError(uiState)
            }
        }
    }

    private fun onLoad() = with(binding) {
        progressBar.setVisible()
        textViewCalculationDuration.text = ""
        btnCalculateOnMain.isEnabled = false
        btnCalculateOnMainUsingYield.isEnabled = false
        btnCalculateWithDefaultDispatcher.isEnabled = false
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        progressBar.setGone()
        btnCalculateOnMain.isEnabled = true
        btnCalculateOnMainUsingYield.isEnabled = true
        btnCalculateWithDefaultDispatcher.isEnabled = true
        textViewCalculationDuration.text =
            "${uiState.thread}: Calculation took ${uiState.duration}ms"
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        progressBar.setGone()
        btnCalculateOnMain.isEnabled = true
        btnCalculateOnMainUsingYield.isEnabled = true
        btnCalculateWithDefaultDispatcher.isEnabled = true
        toast(uiState.message)
    }
}
