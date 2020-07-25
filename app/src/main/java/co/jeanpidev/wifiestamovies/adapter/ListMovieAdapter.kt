package co.jeanpidev.wifiestamovies.adapter

import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import co.jeanpidev.wifiestamovies.databinding.LayoutLoadingItemBinding
import co.jeanpidev.wifiestamovies.databinding.LayoutMovieItemBinding
import co.jeanpidev.wifiestamovies.model.Movie
import co.jeanpidev.wifiestamovies.ui.moviedetail.MovieDetailActivity
import co.jeanpidev.wifiestamovies.utils.EXTRA_MOVIE_ID
import co.jeanpidev.wifiestamovies.utils.EXTRA_MOVIE_NAME
import co.jeanpidev.wifiestamovies.utils.VIEW_TYPE_ITEM
import co.jeanpidev.wifiestamovies.utils.VIEW_TYPE_LOADING
import co.jeanpidev.wifiestamovies.utils.extension.setImage

class ListMovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var movies = ArrayList<Movie?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view: LayoutMovieItemBinding = LayoutMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemViewHolder(view)
        } else {
            val view: LayoutLoadingItemBinding = LayoutLoadingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_ITEM) {
            movies[position]?.let { movie ->
                (holder as ItemViewHolder).bind(movie)
                holder.itemView.setOnClickListener {
                    val intent = Intent(holder.itemView.context, MovieDetailActivity::class.java).apply {
                        putExtra(EXTRA_MOVIE_ID, movie.id)
                        putExtra(EXTRA_MOVIE_NAME, movie.title)
                    }
                    startActivity(holder.itemView.context, intent, null)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (movies[position] == null) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    fun getItemAtPosition(position: Int): Movie? {
        return movies[position]
    }

    fun addLoadingView() {
        //Add loading item
        Handler().post {
            movies.add(null)
            notifyItemInserted(movies.size - 1)
        }
    }

    fun removeLoadingView() {
        //Remove loading item
        if (movies.size != 0) {
            movies.removeAt(movies.size - 1)
            notifyItemRemoved(movies.size)
        }
    }

    fun updateMovieList(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    fun addData(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class ItemViewHolder(private val binding: LayoutMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.tvDescription.text = movie.description
            binding.ivPicture.setImage(movie.image)
        }
    }

    class LoadingViewHolder(private val binding: LayoutLoadingItemBinding) : RecyclerView.ViewHolder(binding.root)
}
