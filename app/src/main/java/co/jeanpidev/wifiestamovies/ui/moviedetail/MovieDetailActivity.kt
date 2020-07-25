package co.jeanpidev.wifiestamovies.ui.moviedetail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import co.jeanpidev.wifiestamovies.adapter.CastAdapter
import co.jeanpidev.wifiestamovies.base.BaseActivity
import co.jeanpidev.wifiestamovies.databinding.ActivityMovieDetailBinding
import co.jeanpidev.wifiestamovies.model.Movie
import co.jeanpidev.wifiestamovies.repository.Result
import co.jeanpidev.wifiestamovies.utils.EXTRA_MOVIE_ID
import co.jeanpidev.wifiestamovies.utils.EXTRA_MOVIE_NAME
import co.jeanpidev.wifiestamovies.utils.extension.app
import co.jeanpidev.wifiestamovies.utils.extension.setImage
import javax.inject.Inject

class MovieDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieDetailViewModel

    private val binding: ActivityMovieDetailBinding by lazy { ActivityMovieDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        app.component.movieDetailComponent().create().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        val movieName = intent.getStringExtra(EXTRA_MOVIE_NAME)
        supportActionBar?.title = movieName

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        viewModel.getMovie(movieId)

        initObservers()
    }

    private fun initObservers() {
        viewModel.getMovie().observe(this, Observer {
            when (it) {
                is Result.Progress -> toggleProgress(it.loading)
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
    }

    private fun populateData(movie: Movie) {
        binding.tvTitle.text = movie.title
        binding.tvDescription.text = movie.description
        binding.ivPicture.setImage(movie.image)
        binding.tvReleaseDate.text = movie.releaseDate
        binding.tvStatus.text = movie.status
        binding.tvReviewCount.text = movie.reviews?.size?.toString() ?: "0"

        movie.cast?.let {
            val adapter = CastAdapter(it)
            binding.rvCast.layoutManager = GridLayoutManager(this, 3)
            binding.rvCast.setHasFixedSize(true)
            binding.rvCast.adapter = adapter
        }
    }
}
