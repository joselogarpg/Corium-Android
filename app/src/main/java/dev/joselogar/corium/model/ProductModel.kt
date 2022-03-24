package dev.joselogar.corium.model

data class ProductModel(
    val available: Boolean,
    val image: List<String>,
    val color_image: List<String>,
    val color_name: List<String>,
    val company: String,
    val model: String,
    val year: Int,
    val price: Double,
    val design: String?,
    val details: String?
)