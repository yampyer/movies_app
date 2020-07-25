package co.jeanpidev.wifiestamovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.jeanpidev.wifiestamovies.databinding.LayoutCharacterItemBinding
import co.jeanpidev.wifiestamovies.model.Character
import co.jeanpidev.wifiestamovies.utils.extension.setImage

class CastAdapter(private val characters: List<Character>) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {
    private var cast = ArrayList<Character>(characters)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: LayoutCharacterItemBinding = LayoutCharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cast[position])
    }

    override fun getItemCount(): Int {
        return cast.size
    }

    class ViewHolder(private val binding: LayoutCharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.tvName.text = character.actor
            binding.ivPicture.setImage(character.image)
        }
    }
}
