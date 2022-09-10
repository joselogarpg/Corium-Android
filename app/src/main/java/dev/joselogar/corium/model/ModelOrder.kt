package dev.joselogar.corium.model

data class ModelOrder(
    val date: String,
    val inProgress: Boolean,
    val model: String,
    val quantity: Int,
    val uid: String
){
    constructor() : this(
        "",
        false,
        "",
        -1,
        ""
    )
}