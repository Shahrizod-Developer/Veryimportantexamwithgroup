package uz.gita.veryimportantexamwithgroup.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.veryimportantexamwithgroup.R
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.databinding.ListItemBinding
import uz.gita.veryimportantexamwithgroup.utils.inflate

class StoresAdapter : ListAdapter<StoreData, StoresAdapter.ViewHolder>(itemCallback) {

    private var itemEditClickListener: ((StoreData) -> Unit)? = null

    private var itemDeleteClickListener: ((StoreData) -> Unit)? = null

    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.imgEdit.setOnClickListener {
                itemEditClickListener?.invoke(getItem(absoluteAdapterPosition))
            }

            binding.imgDelete.setOnClickListener {
                itemDeleteClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun onBind() {
            binding.name.text = getItem(absoluteAdapterPosition).name
            binding.login.text = getItem(absoluteAdapterPosition).login
            binding.password.text = getItem(absoluteAdapterPosition).password
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemBinding.bind(parent.inflate(R.layout.list_item))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()


    fun setItemEditListener(block: (StoreData) -> Unit) {
        itemEditClickListener = block
    }

    fun setItemDeleteListener(block: (StoreData) -> Unit) {
        itemDeleteClickListener = block
    }

}

val itemCallback = object : DiffUtil.ItemCallback<StoreData>() {
    override fun areItemsTheSame(oldItem: StoreData, newItem: StoreData) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: StoreData, newItem: StoreData) =
        oldItem.id == newItem.id
                && oldItem.name == newItem.name
                && oldItem.login == newItem.login
                && oldItem.password == newItem.password

}