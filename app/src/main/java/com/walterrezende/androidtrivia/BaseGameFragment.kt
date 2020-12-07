package com.walterrezende.androidtrivia

import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.walterrezende.androidtrivia.score.GameScoreHandler
import com.walterrezende.androidtrivia.score.ScoreHandler

/**
 * Base class for GameFragments, auto performing inflation, binding and setting hasOptionMenu
 */
abstract class BaseGameFragment<T : ViewDataBinding>(
    protected val scoreHandler: ScoreHandler = GameScoreHandler()
) : Fragment() {

    /**
     * @layoutIdRes Layout id for view to be inflated for current fragment
     */
    @get:LayoutRes
    abstract val layoutIdRes: Int

    /**
     * @menuIdRes Menu id for menu to be inflated for current fragment,
     * will be null if not overridden
     */
    @get:MenuRes
    open val menuIdRes: Int? = null

    /**
     * @hasOptionMenu boolean indication whether or not the fragment will use optionMenu,
     * will be false if not overridden
     */
    open val hasOptionMenu: Boolean = false

    /**
     * @onViewInflated -> lambda to run when the view has been inflated with binding
     */
    open val onViewInflated: (T) -> Unit = {}

    /**
     * @onOptionsMenuInflated -> lambda to run when the menu has been inflated
     */
    open val onOptionsMenuInflated: (menu: Menu, inflater: MenuInflater) -> Unit = { _, _ -> }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: T = DataBindingUtil.inflate(
            inflater, layoutIdRes, container, false
        )

        setHasOptionsMenu(hasOptionMenu)
        onViewInflated(binding)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menuIdRes?.let { id -> inflater.inflate(id, menu) }
        onOptionsMenuInflated(menu, inflater)
    }
}