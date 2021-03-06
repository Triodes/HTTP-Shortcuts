package ch.rmy.android.http_shortcuts.realm.models

import io.realm.RealmList
import io.realm.RealmObject

open class Base : RealmObject() {

    var version: Long = 4
    var categories: RealmList<Category>? = null
    var variables: RealmList<Variable>? = null

}
