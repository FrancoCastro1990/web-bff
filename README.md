# 🌐 **Web BFF - Backend for Frontend Web**

## 🎯 **Descripción**

**Backend for Frontend (BFF) especializado para aplicaciones web** del Banco XYZ. Optimizado para proporcionar **datos completos y detallados** a interfaces web que requieren información rica y completa para una experiencia de usuario avanzada.

## ✅ **Características del Web BFF**

### 🎨 **Optimizado para Web**
- ✅ **Datos completos**: Toda la información disponible de cuentas
- ✅ **Campos adicionales**: `currency`, `balanceAsDouble`, `transactionHistory`
- ✅ **Interfaces ricas**: Soporte para dashboards complejos
- ✅ **Alta performance**: Optimizado para conexiones de banda ancha

### 🔐 **Seguridad Web**
- ✅ **JWT Authentication**: Tokens específicos para canal web
- ✅ **Role-based Access**: `ROLE_WEB` con permisos completos
- ✅ **Session Management**: Manejo de sesiones web prolongadas

## 🚀 **Inicio Rápido**

### Prerrequisitos
- **Java 17+**
- **Maven 3.9+**
- **Backend_03 ejecutándose** (puerto 8084)

### Ejecutar Web BFF
```bash
cd web-bff
./mvnw spring-boot:run
```

**Puerto por defecto**: `8081`

### Verificar Health Check
```bash
curl http://localhost:8081/actuator/health
```

## 🔑 **Autenticación**

### Obtener Token para Web
```bash
curl -X POST http://localhost:8081/auth/token \
  -H "Content-Type: application/json" \
  -d '{"user":"admin","channel":"web"}'
```

### Usar Token en Requests
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8081/web/accounts/124
```

## 📊 **Endpoints Web BFF**

### 👤 **Información de Cuenta Completa**
```bash
# Lista completa de cuentas
GET /web/accounts

# Datos completos de una cuenta específica
GET /web/accounts/{accountNumber}

# Ejemplo de respuesta completa:
{
  "accountNumber": "124",
  "ownerName": "Diana Prince",
  "balance": 15000.00,
  "currency": "USD",
  "accountType": "CHECKING",
  "status": "ACTIVE",
  "createdDate": "2023-01-15",
  "lastTransaction": "2024-09-15"
}
```

## 🧪 **Ejemplos de Uso**

### 1. Dashboard de Cuenta Web
```bash
# Obtener datos para dashboard completo
TOKEN=$(curl -s -X POST http://localhost:8081/auth/token \
  -H "Content-Type: application/json" \
  -d '{"user":"admin","channel":"web"}' | jq -r '.token')

# Lista de cuentas para selección
curl -H "Authorization: Bearer $TOKEN" \
  http://localhost:8081/web/accounts

# Detalles de cuenta seleccionada
curl -H "Authorization: Bearer $TOKEN" \
  http://localhost:8081/web/accounts/124
```

### 2. Verificación End-to-End
```bash
# Script completo de verificación
echo "=== VERIFICACIÓN WEB BFF ==="

# 1. Obtener token
TOKEN=$(curl -s -X POST http://localhost:8081/auth/token \
  -H "Content-Type: application/json" \
  -d '{"user":"admin","channel":"web"}' | jq -r '.token')

echo "Token obtenido: ${TOKEN:0:50}..."

# 2. Verificar lista de cuentas
echo "Lista de cuentas:"
curl -s -H "Authorization: Bearer $TOKEN" \
  http://localhost:8081/web/accounts | jq '.[0]'

# 3. Verificar datos de cuenta específica
echo "Datos de cuenta 124:"
curl -s -H "Authorization: Bearer $TOKEN" \
  http://localhost:8081/web/accounts/124 | jq '.'
```

## 🏗️ **Arquitectura Técnica**

### Stack Tecnológico
- **Spring Boot 3.3.2** - Framework principal
- **Spring WebFlux** - Cliente HTTP reactivo para backend_03
- **Spring Security** - Gestión de autenticación JWT
- **Resilience4j** - Circuit breaker y retry logic

### Estructura del Proyecto
```
web-bff/
├── src/main/java/cl/duoc/web/
│   ├── WebBffApplication.java
│   ├── controller/
│   │   └── WebAccountController.java    # Endpoints /web/*
│   ├── service/
│   │   ├── WebAccountService.java       # Lógica de negocio web
│   │   └── JwtAuthService.java          # Gestión de tokens
│   └── config/
│       └── WebClientConfig.java         # Cliente HTTP para backend_03
├── src/main/resources/
│   ├── application.yml                  # Config puerto 8081
│   └── bank_legacy_data_sample.json     # Datos de muestra
└── pom.xml                              # Dependencias Maven
```

### Optimizaciones Web
- ✅ **Datos enriquecidos**: Campos adicionales para UI compleja
- ✅ **Paginación**: Soporte para listas grandes
- ✅ **Filtros avanzados**: Búsqueda y ordenamiento
- ✅ **Caching**: Cache de datos frecuentes
- ✅ **Compresión**: Gzip para respuestas grandes

## 🔍 **Monitoreo y Health Checks**

### Health Check
```bash
curl http://localhost:8081/actuator/health
```

### Métricas Disponibles
```bash
# Métricas de rendimiento
curl http://localhost:8081/actuator/metrics

# Información del servicio
curl http://localhost:8081/actuator/info
```

## 📈 **Performance**

### Benchmarks Esperados
- **Response Time**: < 200ms para datos de cuenta
- **Throughput**: 1000+ requests/segundo
- **Payload Size**: Optimizado para conexiones de banda ancha
- **Concurrent Users**: Soporte para 1000+ usuarios simultáneos

### Optimizaciones Implementadas
- ✅ **Connection Pooling**: Reutilización de conexiones HTTP
- ✅ **Async Processing**: Procesamiento asíncrono de requests
- ✅ **Response Compression**: Compresión automática de respuestas
- ✅ **Circuit Breaker**: Protección contra fallos del backend

## 🚨 **Troubleshooting**

### Error Común: Token Expirado
```bash
# Si obtienes 401 Unauthorized, renueva el token
curl -X POST http://localhost:8081/auth/token \
  -H "Content-Type: application/json" \
  -d '{"user":"admin","channel":"web"}'
```

### Error: Backend_03 No Disponible
```bash
# Verificar que backend_03 esté ejecutándose
curl http://localhost:8084/actuator/health

# Si no responde, iniciar backend_03
cd ../backend_03
./mvnw spring-boot:run
```

### Logs de Debug
```bash
# Ver logs en tiempo real
tail -f logs/web-bff.log

# Con debug habilitado
curl -H "Authorization: Bearer $TOKEN" \
  "http://localhost:8081/web/accounts/124?debug=true"
```

## 🎯 **Rol en Arquitectura BFF**

```
Usuario Web ──► Web BFF ──► Backend_03
                    │
                    └──► Datos completos + UI rica
```

**Responsabilidades**:
- ✅ **Adaptación de datos** para interfaces web
- ✅ **Enriquecimiento** con campos adicionales
- ✅ **Optimización** para UX web avanzada
- ✅ **Seguridad** específica para canal web

---

**🚀 Web BFF operativo y optimizado para experiencias web excepcionales**

