package com.carolina.myapplication.usecases.coroutines.usecase12

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.carolina.myapplication.R
import com.carolina.myapplication.base.BaseActivity
import com.carolina.myapplication.base.useCase12Description
import com.carolina.myapplication.databinding.ActivityCalculationinmultiplebackgroundthreadsBinding
import com.carolina.myapplication.utils.hideKeyboard
import com.carolina.myapplication.utils.setGone
import com.carolina.myapplication.utils.setVisible
import com.carolina.myapplication.utils.toast
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase12.CalculationInSeveralCoroutinesViewModel
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase12.UiState

class CalculationInSeveralCoroutinesActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase12Description

    private val binding by lazy {
        ActivityCalculationinmultiplebackgroundthreadsBinding.inflate(
            layoutInflater,
        )
    }

    private val viewModel: CalculationInSeveralCoroutinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.uiState()
            .observe(
                this,
                Observer { uiState ->
                    if (uiState != null) {
                        render(uiState)
                    }
                },
            )
        binding.btnCalculate.setOnClickListener {
            val factorialOf = binding.editTextFactorialOf.text.toString().toIntOrNull()
            val numberOfThreads = binding.editTextNumberOfThreads.text.toString().toIntOrNull()
            if (factorialOf != null && numberOfThreads != null) {
                viewModel.performCalculation(
                    factorialOf,
                    numberOfThreads,
                )
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
        textViewResult.text = ""
        textViewDuration.text = ""
        textViewStringConversionDuration.text = ""
        btnCalculate.isEnabled = false
        textViewResult.hideKeyboard()
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        textViewDuration.text =
            getString(R.string.duration_calculation, uiState.computationDuration)
        textViewStringConversionDuration.text =
            getString(R.string.duration_stringconversion, uiState.stringConversionDuration)
        progressBar.setGone()
        btnCalculate.isEnabled = true
        textViewResult.text = if (uiState.result.length <= 150) {
            uiState.result
        } else {
            "${uiState.result.substring(0, 147)}..."
        }
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        progressBar.setGone()
        btnCalculate.isEnabled = true
        toast(uiState.message)
    }
}
