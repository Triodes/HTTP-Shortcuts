package ch.rmy.android.http_shortcuts.listeners

interface OnItemClickedListener<T> {

    fun onItemClicked(item: T)

    fun onItemLongClicked(item: T)

}
