package br.com.pokemon.exception;

import br.com.pokemon.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Trata exceções de Pokémon não encontrado
     */
    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePokemonNotFound(
            PokemonNotFoundException ex, 
            WebRequest request) {
        
        logger.warn("Pokémon não encontrado: {}", ex.getMessage());
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Trata exceções da PokeAPI
     */
    @ExceptionHandler(PokeApiException.class)
    public ResponseEntity<ErrorResponse> handlePokeApiException(
            PokeApiException ex, 
            WebRequest request) {
        
        logger.error("Erro ao consultar PokeAPI: {}", ex.getMessage(), ex);
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_GATEWAY.value(),
            "Bad Gateway",
            "Erro ao consultar API externa: " + ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
    }

    /**
     * Trata erros de validação (@Valid)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        
        List<String> details = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.add(error.getField() + ": " + error.getDefaultMessage());
        }
        
        logger.warn("Erro de validação: {}", details);
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            "Erro de validação nos campos",
            request.getDescription(false).replace("uri=", ""),
            details
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Trata erro de conversão de tipo (ex: passar texto onde espera número)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            WebRequest request) {
        
        logger.warn("Erro de tipo de argumento: {} - esperado: {}, recebido: {}", 
            ex.getName(), ex.getRequiredType(), ex.getValue());
        
        String message = String.format(
            "Parâmetro '%s' deve ser do tipo %s, mas recebeu: %s",
            ex.getName(),
            ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconhecido",
            ex.getValue()
        );
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            message,
            request.getDescription(false).replace("uri=", "")
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Trata IllegalArgumentException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex,
            WebRequest request) {
        
        logger.warn("Argumento inválido: {}", ex.getMessage());
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Trata qualquer exceção não prevista
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex,
            WebRequest request) {
        
        logger.error("Erro interno não tratado: ", ex);
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            "Ocorreu um erro interno no servidor. Por favor, contate o suporte.",
            request.getDescription(false).replace("uri=", "")
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
