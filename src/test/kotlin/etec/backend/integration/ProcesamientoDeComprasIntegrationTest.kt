
package etec.backend.integration

import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ProcesamientoDeComprasIntegrationTest(
    private val navegador: MockMvc
) : BehaviorSpec({

    Given("el sistema completo de procesamiento de compras") {

        When("un cliente compra por 1000 usando el cupón BLACK_FRIDAY") {

            val respuesta = navegador.perform(
                get("/api/compras/procesar")
                    .param("subtotal", "1000.0")
                    .param("cupon", "BLACK_FRIDAY")
            )

            Then("debe responder HTTP 200") {
                respuesta.andExpect(status().isOk)
            }

            And("debe devolver un monto final de 700") {
                respuesta.andExpect(
                    jsonPath("$.montoFinal").value(700.0)
                )
            }
        }
    }
})
