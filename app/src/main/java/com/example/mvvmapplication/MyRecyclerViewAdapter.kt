package com.example.mvvmapplication

import android.widget.Toast
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapplication.databinding.RecyclerviewItemBinding


class MyRecyclerViewAdapter(
    private val dataModelList: List<PostModel>,
    private val context: Context
) : RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>(), CardClickListener {


    override fun getItemCount(): Int {
        return dataModelList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DataBindingUtil.inflate<RecyclerviewItemBinding>(
            LayoutInflater.from(parent.context),
        R.layout.recyclerview_item, parent, false
       )

       return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataModelList[position]
        holder.bind(dataModel)
        //holder.itemRowBinding.itemClickListener = this
    }

    inner class ViewHolder(var itemRowBinding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(itemRowBinding.root) {
        fun bind(obj: Any) {
            itemRowBinding.setVariable(BR.model, obj)
            itemRowBinding.executePendingBindings()
        }
    }
    override fun cardClicked(f: PostModel) {
        Toast.makeText(
            context, "You clicked " + f.title,
            Toast.LENGTH_LONG
        ).show()
    }
}