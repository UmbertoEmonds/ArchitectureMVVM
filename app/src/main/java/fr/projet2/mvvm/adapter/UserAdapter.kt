package fr.projet2.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.projet2.mvvm.R
import fr.projet2.mvvm.model.User

class UserAdapter(private var users: List<User>, private val listener: OnItemClickListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun update(users: List<User>){
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_line, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.mNameTV.text = "${users[position].firstname} ${users[position].lastname}"
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val mNameTV : TextView = view.findViewById(R.id.mTitleTV)
        val mDeleteIV : ImageView = view.findViewById(R.id.deleteItem)

        init {
            mDeleteIV.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClick(adapterPosition)
        }
    }

}

interface OnItemClickListener {
    fun onClick(position: Int)
}