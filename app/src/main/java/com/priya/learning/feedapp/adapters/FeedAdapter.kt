package com.priya.learning.feedapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.priya.learning.feedapp.data.Post
import com.priya.learning.feedapp.databinding.ItemFeedBinding
import com.priya.learning.feedapp.fragment.FeedsFragmentDirections
import com.priya.learning.feedapp.utility.TRANSACTIONS
import kotlinx.android.synthetic.main.item_list_footer.view.*


/**
 * Adapter for the [RecyclerView] in [FeedsFragmentList].
 */
class NewsAdapter : PagingDataAdapter<Post, RecyclerView.ViewHolder>(NewsDiffCallback()) {

//    private var state = State.LOADING
//    private val FOOTER_VIEW_TYPE = 2
//    private val DATA_VIEW_TYPE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        plant?.let { (holder as NewsViewHolder).bind(it) }
    }

    class NewsViewHolder(
        private val binding: ItemFeedBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.item?.let { news ->
                    navigateToPlant(news, it)
                }
            }
        }

        private fun navigateToPlant(
            post: Post,
            view: View
        ) {
            val direction = FeedsFragmentDirections.actionFeedsFragmentToFeedDetailsFragment(post)
            val extras = FragmentNavigatorExtras(binding.root to TRANSACTIONS)
            view.findNavController().navigate(direction,extras)
        }


        fun bind(item: Post) {
            binding.apply {
                this.item = item
                this.root.transitionName = "feed_transition_container_"+item?.id.toString()
                executePendingBindings()
            }
        }
    }
}

private class NewsDiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}