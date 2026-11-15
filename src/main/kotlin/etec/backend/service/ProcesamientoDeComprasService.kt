
package etec.backend.service

import org.springframework.stereotype.Service

@Service
class ProcesamientoDeComprasService {

    fun procesarCompra(
        subtotal: Double,
        cupon: String
    ): Double {

        if (cupon == "BLACK_FRIDAY") {
            return subtotal * 0.70
        }

        return subtotal
    }
}
