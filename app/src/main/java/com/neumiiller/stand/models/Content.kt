package com.neumiiller.stand.models

/**
 * Content is a model for the content that a user would write out
 */
class Content(public val raw: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Content

        if (raw != other.raw) return false

        return true
    }

    override fun hashCode(): Int {
        return raw.hashCode()
    }
}