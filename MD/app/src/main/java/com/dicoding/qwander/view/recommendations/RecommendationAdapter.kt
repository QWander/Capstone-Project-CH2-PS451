package com.dicoding.qwander.view.recommendations

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.qwander.R
import com.dicoding.qwander.data.response.RecommendationsItem
import com.dicoding.qwander.databinding.RecomendationItemBinding
import com.dicoding.qwander.view.DetailActivity
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class RecommendationAdapter : ListAdapter<RecommendationsItem, RecommendationAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = RecomendationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }




    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recommendation = getItem(position)
        holder.bind(recommendation)
    }



    class MyViewHolder(private val binding: RecomendationItemBinding): RecyclerView.ViewHolder(binding.root) {

        private var name: TextView = itemView.findViewById(R.id.tv_attraction_name)
        private var rating: TextView = itemView.findViewById(R.id.tv_attraction_rating1)
        private var price: TextView = itemView.findViewById(R.id.tv_price)


        fun bind(recommendation: RecommendationsItem) {

            Log.i(TAG, "datanya adapter3")
            name.text = recommendation.placeName.toString()
            rating.text = recommendation.rating.toString()
            price.text = formatAsRupiah(recommendation.price)


            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("Recommendation", recommendation)

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(name, "tv_attraction_name"),
                        Pair(rating, "tv_attraction_rating1"),
                        Pair(price, "tv_price"),
                    )
                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }

        private fun formatAsRupiah(amount: Int): String {
            val indonesiaLocale = Locale("id", "ID") // Create a Locale for Indonesia
            val format = NumberFormat.getCurrencyInstance(indonesiaLocale)

            val rupiahCurrency = Currency.getInstance("IDR") // Get the currency instance for IDR
            format.currency = rupiahCurrency

            return format.format(amount)
        }
    }






    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecommendationsItem>() {
            override fun areItemsTheSame(oldItem: RecommendationsItem, newItem: RecommendationsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RecommendationsItem,
                newItem: RecommendationsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}