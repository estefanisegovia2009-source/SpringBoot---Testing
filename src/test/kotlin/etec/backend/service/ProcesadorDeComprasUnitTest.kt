package etec.backend.service
import etec.backend.repository.CuponRepository
import etec.backend.exception.PrecondicionInvalidaException
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.every

class ProcesadorDeComprasUnitTest : BehaviorSpec({

    Given("un servicio de procesamiento de compras") {

        val repositorio = mockk<CuponRepository>()
        val servicio = ProcesamientoDeComprasService(repositorio)

        When("el monto total es cero") {

            Then("debe lanzar una excepción") {

                val error = shouldThrow<PrecondicionInvalidaException> {
                    servicio.procesarCompra(0.0, "")
                }

                error.message shouldBe
                    "El monto total de la compra debe ser mayor a cero"
            }
        }

        When("el monto total es negativo") {

            Then("debe lanzar una excepción") {

                val error = shouldThrow<PrecondicionInvalidaException> {
                    servicio.procesarCompra(-50.0, "")
                }

                error.message shouldBe
                    "El monto total de la compra debe ser mayor a cero"
            }
        }
    }
})