package co.jeanpidev.wifiestamovies.ui.listmovie

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.jeanpidev.wifiestamovies.R
import co.jeanpidev.wifiestamovies.adapter.ListMovieAdapter
import co.jeanpidev.wifiestamovies.base.BaseActivity
import co.jeanpidev.wifiestamovies.databinding.ActivityListMovieBinding
import co.jeanpidev.wifiestamovies.model.Movie
import co.jeanpidev.wifiestamovies.repository.Result
import co.jeanpidev.wifiestamovies.utils.*
import co.jeanpidev.wifiestamovies.utils.custom.OnLoadMoreListener
import co.jeanpidev.wifiestamovies.utils.custom.RecyclerViewLoadMoreScroll
import co.jeanpidev.wifiestamovies.utils.extension.app
import javax.inject.Inject

class ListMovieActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ListMovieViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var scrollListener: RecyclerViewLoadMoreScroll

    private val binding: ActivityListMovieBinding by lazy { ActivityListMovieBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        app.component.listMovieComponent().create().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListMovieViewModel::class.java)
        viewModel.getAllMovies()

        initUI()
        initObservers()
    }

    private fun initUI() {
        binding.btnSelectSort.setOnClickListener {
            viewModel.getSortVisibility().value?.let {
                viewModel.getSortVisibility().value = !it
            }
        }

        binding.radioGroupSortType.setOnCheckedChangeListener { _, checkedId ->
            viewModel.getSortValue().value = when (checkedId) {
                R.id.radio_title_asc -> {
                    SORT_TITLE_ASC
                }
                R.id.radio_title_desc -> {
                    SORT_TITLE_DESC
                }
                R.id.radio_date_asc -> {
                    SORT_DATE_ASC
                }
                R.id.radio_date_desc -> {
                    SORT_DATE_DESC
                }
                R.id.radio_popularity_asc -> {
                    SORT_POPULARITY_ASC
                }
                R.id.radio_popularity_desc -> {
                    SORT_POPULARITY_DESC
                }
                else -> ""
            }
            viewModel.getSortVisibility().value = false
        }

        binding.btnSearch.setOnClickListener {
            viewModel.getQueryValue().value = binding.tvSearchQuery.text.toString()
        }

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMovies.layoutManager = linearLayoutManager
        binding.rvMovies.setHasFixedSize(true)

        setScrollListener()

        binding.rvMovies.adapter = ListMovieAdapter()
    }

    private fun initObservers() {
        viewModel.getMovieList().observe(this, Observer {
            when (it) {
                is Result.Progress -> {
                    if (viewModel.getPage() == 1)
                        toggleProgress(it.loading)
                }
                is Result.Failure -> {
                    toggleProgress(false)
                    showError("An error has occurred")
                }
                is Result.Success -> {
                    toggleProgress(false)
                    populateData(it.data)
                }
            }
        })
        viewModel.getSortVisibility().observe(this, Observer {
            binding.radioGroupSortType.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.getSortValue().observe(this, Observer {
            viewModel.getAllMovies()
        })
        viewModel.getQueryValue().observe(this, Observer {
            viewModel.getAllMovies()
        })
    }

    private fun setScrollListener() {
        linearLayoutManager = LinearLayoutManager(this)
        scrollListener = RecyclerViewLoadMoreScroll(linearLayoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                (binding.rvMovies.adapter as ListMovieAdapter).addLoadingView()
                viewModel.getNextPage()
            }
        })
        binding.rvMovies.addOnScrollListener(scrollListener)
    }

    private fun populateData(movies: List<Movie>) {
        if (viewModel.getPage() == 1) {
            if (movies.isNotEmpty()) {
                binding.tvNoResults.visibility = View.GONE
                binding.rvMovies.visibility = View.VISIBLE
                (binding.rvMovies.adapter as ListMovieAdapter).updateMovieList(movies)
            } else {
                binding.rvMovies.visibility = View.GONE
                binding.tvNoResults.visibility = View.VISIBLE
            }
        } else {
            (binding.rvMovies.adapter as ListMovieAdapter).removeLoadingView()
            (binding.rvMovies.adapter as ListMovieAdapter).addData(movies)
        }
    }
}
