package com.dicoding.qwander.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.qwander.data.repository.RecommendationRepository
import com.dicoding.qwander.di.Injection
import com.dicoding.qwander.view.recommendations.RecommendationViewModel

class ViewModelFactory(
    private val recommendationRepository: RecommendationRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return when {
            modelClass.isAssignableFrom(RecommendationViewModel::class.java) -> {
                RecommendationViewModel(recommendationRepository) as T
            }

            else -> {throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)}
        }


    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    val recommendationRepository = Injection.provideRepository(context)
                    INSTANCE = ViewModelFactory(recommendationRepository)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

}