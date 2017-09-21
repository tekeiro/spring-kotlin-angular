@file:JvmName("SpringExtensions")
package org.keirobm.samples.springangularbackend.utils

import org.springframework.ui.Model


operator fun Model.set(key: String, value: Any) {
    this.addAttribute(key, value)
}
