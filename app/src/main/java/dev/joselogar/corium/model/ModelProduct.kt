package dev.joselogar.corium.model

data class ModelProduct(
    val available: Boolean,
    val colorImage: List<String>,
    val colorName: List<String>,
    val company: String,
    val image: List<String>,
    val model: String,
    val price: Double,
    val uid: String,
    val year: Int
){
    constructor() : this(
        false,
        emptyList(),
        emptyList(),
        "",
        emptyList(),
        "",
        -1.0,
        "",
        -1
    )
}