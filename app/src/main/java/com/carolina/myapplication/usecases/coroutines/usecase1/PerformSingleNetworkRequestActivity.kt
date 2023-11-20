package com.carolina.myapplication.usecases.coroutines.usecase1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.carolina.myapplication.base.BaseActivity
import com.carolina.myapplication.base.useCase1Description
import com.carolina.myapplication.databinding.ActivityPerformsinglenetworkrequestBinding
import com.carolina.myapplication.utils.fromHtml
import com.carolina.myapplication.utils.setGone
import com.carolina.myapplication.utils.setVisible
import com.carolina.myapplication.utils.toast

class PerformSingleNetworkRequestActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase1Description

    private val binding by lazy { ActivityPerformsinglenetworkrequestBinding.inflate(layoutInflater) }
    private val viewModel: PerformSingleNetworkRequestViewModel by viewModels()

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
        binding.btnPerformSingleNetworkRequest.setOnClickListener {
            viewModel.performSingleNetworkRequest()
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
        btnPerformSingleNetworkRequest.isEnabled = false
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        progressBar.setGone()
        btnPerformSingleNetworkRequest.isEnabled = true
        val readableVersions = uiState.recentVersions.map { "API ${it.apiLevel}: ${it.name}" }
        textViewResult.text = fromHtml(
            "<b>Recent Android Versions</b><br>${readableVersions.joinToString(separator = "<br>")}",
        )
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        progressBar.setGone()
        btnPerformSingleNetworkRequest.isEnabled = true
        toast(uiState.message)
    }
}
