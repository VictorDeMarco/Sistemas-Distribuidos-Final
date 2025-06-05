package com.example.usuarios.usuarios.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
/**
 * Clase ApiTestController.
 */
public class ApiTestController {

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String apiBaseUrl = "http://flask-api:5000/api";

    @GetMapping("/api-test")
/**
 * Método mostrarFormularioApiTest.
 * @return valor de retorno
 */
    public String mostrarFormularioApiTest() {
        return "api-test"; // nombre del HTML en templates
    }
    @PostMapping("/api-test")
/**
 * Método consumirApi.
 * @param tipo parámetro de entrada
 * @param model parámetro de entrada
 * @return valor de retorno
 */
    public String consumirApi(@RequestParam String tipo, Model model) {
        String url = apiBaseUrl + tipo;
        String urlreddirect = "http://localhost:5000/api";
        String urlreddirect_tipo = urlreddirect + tipo;
        model.addAttribute("endpointLlamado", urlreddirect_tipo);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            model.addAttribute("mensaje", "✅ Solicitud completada sin errores");
            model.addAttribute("resultado", response.getBody());
            model.addAttribute("errorDetectado", false);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            model.addAttribute("errorDetectado", true);

            try {
                JsonNode json = objectMapper.readTree(ex.getResponseBodyAsString());
                String tipoError = json.has("error") ? json.get("error").asText() : "Error desconocido";
                String excepcion = json.has("excepcion") ? json.get("excepcion").asText() : "Sin detalles adicionales.";


                HttpStatus status = (HttpStatus) ex.getStatusCode();

                if (status == HttpStatus.BAD_GATEWAY) {
                    model.addAttribute("mensaje", "⚠️ Fallo al contactar con una API externa.");
                } else if (status == HttpStatus.BAD_REQUEST || tipoError.contains("archivo")) {
                    model.addAttribute("mensaje", "📁 No se pudo acceder al archivo solicitado.");
                } else if (status == HttpStatus.INTERNAL_SERVER_ERROR || tipoError.contains("base de datos")) {
                    model.addAttribute("mensaje", "💾 Ocurrió un error en la base de datos.");
                } else {
                    model.addAttribute("mensaje", "❌ Se produjo un error inesperado.");
                }

                model.addAttribute("excepcion", excepcion);
            } catch (Exception e) {
                model.addAttribute("mensaje", "❌ Error inesperado al procesar la respuesta");
                model.addAttribute("excepcion", ex.getResponseBodyAsString());
            }

        } catch (Exception e) {
            model.addAttribute("mensaje", "❌ Error general: No se ha logrado conectar con " + url);
            model.addAttribute("errorDetectado", true);
            model.addAttribute("excepcion", "general");
        }

        return "api-test";
    }
}