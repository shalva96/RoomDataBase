package com.example.todolistapp.view.framgments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.model.storage.UserDemo

class MineAdapter: RecyclerView.Adapter<MineAdapter.MineViewHolder>() {

    private var userList = emptyList<UserDemo>()

    inner class MineViewHolder(view: View): RecyclerView.ViewHolder(view){
        var id = view.findViewById<AppCompatTextView>(R.id.tv)
        var name = view.findViewById<AppCompatTextView>(R.id.name)
        var currentData = view.findViewById<ConstraintLayout>(R.id.item_holder)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineViewHolder {
        return MineViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_holder_demo, parent, false)
        )

    }

    override fun onBindViewHolder(holder: MineViewHolder, position: Int) {
        var currentItem = userList[position]
        holder.id.text = currentItem.id.toString()
        holder.name.text = currentItem.name

        holder.currentData.setOnClickListener {
            listener?.setDataToUpdateFragment(user = currentItem)
        }
    }

    override fun getItemCount() = userList.size

    fun setDate(user: List<UserDemo>) {
        this.userList = user
        notifyDataSetChanged()
    }

    var listener: ISetDataToUpdateFragment? = null

    interface ISetDataToUpdateFragment {
        fun setDataToUpdateFragment(user: UserDemo)
    }

    fun impInterface(myListener: ISetDataToUpdateFragment) {
        this.listener = myListener
    }


}