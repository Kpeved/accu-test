package com.lolkek.core_network.data.weather

import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    @SerializedName("DailyForecasts")
    val dailyForecasts: List<DailyForecast>,
    @SerializedName("Headline")
    val headline: Headline
) {
    data class DailyForecast(
        @SerializedName("Date")
        val date: String,
        @SerializedName("Day")
        val day: Day,
        @SerializedName("EpochDate")
        val epochDate: Int,
        @SerializedName("Link")
        val link: String,
        @SerializedName("MobileLink")
        val mobileLink: String,
        @SerializedName("Night")
        val night: Night,
        @SerializedName("Sources")
        val sources: List<String>,
        @SerializedName("Temperature")
        val temperature: Temperature
    ) {
        data class Temperature(
            @SerializedName("Maximum")
            val maximum: Maximum,
            @SerializedName("Minimum")
            val minimum: Minimum
        ) {
            data class Minimum(
                @SerializedName("Unit")
                val unit: String,
                @SerializedName("UnitType")
                val unitType: Int,
                @SerializedName("Value")
                val value: Float
            )

            data class Maximum(
                @SerializedName("Unit")
                val unit: String,
                @SerializedName("UnitType")
                val unitType: Int,
                @SerializedName("Value")
                val value: Float
            )
        }

        data class Day(
            @SerializedName("Icon")
            val icon: Int,
            @SerializedName("IconPhrase")
            val iconPhrase: String
        )

        data class Night(
            @SerializedName("Icon")
            val icon: Int,
            @SerializedName("IconPhrase")
            val iconPhrase: String
        )
    }

    data class Headline(
        @SerializedName("Category")
        val category: String,
        @SerializedName("EffectiveDate")
        val effectiveDate: String,
        @SerializedName("EffectiveEpochDate")
        val effectiveEpochDate: Int,
        @SerializedName("EndDate")
        val endDate: Any,
        @SerializedName("EndEpochDate")
        val endEpochDate: Any,
        @SerializedName("Link")
        val link: String,
        @SerializedName("MobileLink")
        val mobileLink: String,
        @SerializedName("Severity")
        val severity: Int,
        @SerializedName("Text")
        val text: String
    )
}