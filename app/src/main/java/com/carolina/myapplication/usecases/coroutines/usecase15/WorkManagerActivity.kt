package com.carolina.myapplication.usecases.coroutines.usecase15

import android.os.Bundle
import androidx.activity.viewModels
import com.carolina.myapplication.base.BaseActivity
import com.carolina.myapplication.base.useCase15Description
import com.carolina.myapplication.databinding.ActivityWorkmangerBinding
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase15.ViewModelFactory
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase15.WorkManagerViewModel

class WorkManagerActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase15Description

    private val binding by lazy { ActivityWorkmangerBinding.inflate(layoutInflater) }
    private val viewModel: WorkManagerViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.performAnalyticsRequest()
    }
}
