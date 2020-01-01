package com.example.reddit.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var viewDataBinding: T? = null

    lateinit var viewModel: V

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
//    abstract val viewModel: V
    protected abstract fun provideViewModel(): V

    /**
     * Called to do initial creation of a fragment.
     * <p>
     * Note that this can be called while the fragment's activity is still in the process of
     * being created. As such, you can not rely on things like the activity's content view
     * hierarchy being initialized at this point. If you want to do work once the activity itself
     * is created, see onActivityCreated(Bundle).
     *
     * @param savedInstanceState Bundle: If the fragment is being re-created from a previous saved state, this is the state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
        performDataBinding()

    }

    /**
     * Perform data binding for corresponding view
     */
    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewDataBinding!!.setVariable(bindingVariable, viewModel)
        viewDataBinding!!.executePendingBindings()
    }
}