# Finance Ticker - Task Board

**Generated:** 2026-04-25  
**Status:** In Progress

---

## Current Project Standing

**Overall Progress:** ~5% complete (Epic E1 ~20%)

### What Exists ✅
- Basic Spring Boot 4.0.5 project structure (Maven)
- `FinanceTickerApplication.java` entry point
- Dependencies: actuator, webmvc, websocket
- Test class skeleton

### What's Missing ❌
- No `application.yml` with configuration
- No Docker setup
- No API clients, models, or services
- No WebSocket configuration
- No static resources (frontend)

---

## Phase 1: Foundation & Configuration

| ID | Task | Type | Location | Fields/Parameters                                                          | Status |
|----|------|------|----------|----------------------------------------------------------------------------|--------|
| 1.1 | Create `application.yml` with server port, API key placeholders, CORS | Config | `src/main/resources/application.yml` | `server.port`, `finance-ticker.api-keys.*`, `cors.allowed-origins`         | [ ] |
| 1.2 | Create `TickerPrice` record | Record | `model/TickerPrice.java` | `ticker`, `assetClass`, `lastPrice`, `high`, `low`, `timestamp`, `volume`  | [ ] |
| 1.3 | Create `AssetClass` enum | Enum | `model/AssetClass.java` | `STOCK`, `CRYPTO`, `FOREX`                                                 | [ ] |
| 1.4 | Create `WebSocketMessage` record | Record | `model/WebSocketMessage.java` | `type`, `data`, `tags` (could be the ticker or other tags -human)          | [ ] |
| 1.5 | Create `MessageType` enum | Enum | `model/MessageType.java` | `PRICE_UPDATE`, `SUBSCRIBE`, `UNSUBSCRIBE`, `ERROR`                        | [ ] |
| 1.6 | Create `Dockerfile` (JDK 17 slim) | Config | Root `Dockerfile` | `FROM eclipse-temurin:17-jre-alpine`, `ARG JAR_FILE`, `COPY`, `ENTRYPOINT` | [ ] |
| 1.7 | Create `docker-compose.yml` | Config | Root `docker-compose.yml` | `services.app.image`, `services.app.ports`, `services.app.environment`     | [ ] |

---

## Phase 2: API Clients (Epic E2)

### Crypto Client (CoinGecko)

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 2.1.1 | Create `CoinGeckoClient` class | Class | `client/CoinGeckoClient.java` | `@Component`, `@RequiredArgsConstructor` | [ ] |
| 2.1.2 | Add `httpClient` field (HttpClient) | Field | In `CoinGeckoClient` | `private final HttpClient httpClient` | [ ] |
| 2.1.3 | Add `objectMapper` field (ObjectMapper) | Field | In `CoinGeckoClient` | `private final ObjectMapper objectMapper` | [ ] |
| 2.1.4 | Implement `fetchPrices(List<String> symbols)` method | Method | In `CoinGeckoClient` | Returns `CompletableFuture<List<TickerPrice>>`, param: `List<String> symbols` (coin IDs) | [ ] |
| 2.1.5 | Implement `buildRequest(List<String> symbols)` method | Method | In `CoinGeckoClient` | Returns `HttpRequest`, param: coin IDs comma-separated, endpoint: `/simple/price` | [ ] |
| 2.1.6 | Implement `parseResponse(String json, List<String> symbols)` method | Method | In `CoinGeckoClient` | Returns `List<TickerPrice>`, params: `String json`, `List<String> symbols` | [ ] |
| 2.1.7 | Create `CoinGeckoPriceResponse` record (internal DTO) | Record | `client/dto/CoinGeckoPriceResponse.java` | `usd`, `usd_24h_change` (nested per coin) | [ ] |

### Stock Client (Finnhub)

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 2.2.1 | Create `FinnhubClient` class | Class | `client/FinnhubClient.java` | `@Component`, `@RequiredArgsConstructor` | [ ] |
| 2.2.2 | Add `httpClient` field | Field | In `FinnhubClient` | `private final HttpClient httpClient` | [ ] |
| 2.2.3 | Add `objectMapper` field | Field | In `FinnhubClient` | `private final ObjectMapper objectMapper` | [ ] |
| 2.2.4 | Add `apiKey` field (@Value) | Field | In `FinnhubClient` | `@Value("${finance-ticker.api-keys.finnhub}") private final String apiKey` | [ ] |
| 2.2.5 | Implement `fetchPrice(String symbol)` method | Method | In `FinnhubClient` | Returns `CompletableFuture<TickerPrice>`, param: `String symbol` | [ ] |
| 2.2.6 | Implement `buildQuoteRequest(String symbol)` method | Method | In `FinnhubClient` | Returns `HttpRequest`, endpoint: `/quote`, params: `symbol`, `token=apiKey` | [ ] |
| 2.2.7 | Implement `parseQuote(String json, String symbol)` method | Method | In `FinnhubClient` | Returns `TickerPrice`, params: `String json`, `String symbol` | [ ] |
| 2.2.8 | Create `FinnhubQuoteResponse` record | Record | `client/dto/FinnhubQuoteResponse.java` | `c` (current price), `h` (high), `l` (low), `t` (timestamp) | [ ] |

### Forex Client (ExchangeRate-API)

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 2.3.1 | Create `ForexClient` class | Class | `client/ForexClient.java` | `@Component`, `@RequiredArgsConstructor` | [ ] |
| 2.3.2 | Add `httpClient` field | Field | In `ForexClient` | `private final HttpClient httpClient` | [ ] |
| 2.3.3 | Add `objectMapper` field | Field | In `ForexClient` | `private final ObjectMapper objectMapper` | [ ] |
| 2.3.4 | Add `cache` field (ConcurrentHashMap) | Field | In `ForexClient` | `private final ConcurrentHashMap<String, CachedRate> cache` | [ ] |
| 2.3.5 | Implement `fetchRates(String baseCurrency)` method | Method | In `ForexClient` | Returns `CompletableFuture<List<TickerPrice>>`, param: `String baseCurrency` (e.g., "USD") | [ ] |
| 2.3.6 | Implement `isCacheValid(String key)` method | Method | In `ForexClient` | Returns `boolean`, param: currency pair key, checks age < 5 min | [ ] |
| 2.3.7 | Create `ExchangeRateResponse` record | Record | `client/dto/ExchangeRateResponse.java` | `base`, `conversion_rates` (map of currency → rate) | [ ] |

### Shared HTTP Client

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 2.4.1 | Create `HttpClientConfig` configuration class | Class | `config/HttpClientConfig.java` | `@Configuration`, `@EnableConfigurationProperties` | [ ] |
| 2.4.2 | Implement `httpClient()` bean method | Method | In `HttpClientConfig` | Returns `HttpClient`, configures: `.connectTimeout(Duration.ofSeconds(5))`, `.executor(Executor)` | [ ] |
| 2.4.3 | Implement `objectMapper()` bean method | Method | In `HttpClientConfig` | Returns `ObjectMapper`, configures: `FAIL_ON_UNKNOWN_PROPERTIES=false`, `PropertyNamingStrategies.SNAKE_CASE` | [ ] |

---

## Phase 3: Price Aggregation (Epic E3)

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 3.1 | Create `PriceCache` class | Class | `service/PriceCache.java` | `@Component`, `@Singleton` | [ ] |
| 3.2 | Add `cache` field (ConcurrentHashMap<String, TickerPrice>) | Field | In `PriceCache` | `private final ConcurrentHashMap<String, TickerPrice> cache` | [ ] |
| 3.3 | Implement `put(TickerPrice price)` method | Method | In `PriceCache` | Param: `TickerPrice price`, key: `price.ticker()` | [ ] |
| 3.4 | Implement `get(String symbol)` method | Method | In `PriceCache` | Returns `Optional<TickerPrice>`, param: `String symbol` | [ ] |
| 3.5 | Implement `getAll()` method | Method | In `PriceCache` | Returns `Collection<TickerPrice>` | [ ] |
| 3.6 | Implement `getBySymbols(Set<String> symbols)` method | Method | In `PriceCache` | Returns `List<TickerPrice>`, param: `Set<String> symbols` | [ ] |
| 3.7 | Create `PriceAggregatorService` class | Class | `service/PriceAggregatorService.java` | `@Component`, `@EnableScheduling` | [ ] |
| 3.8 | Add `cryptoClient`, `stockClient`, `forexClient` fields | Fields | In `PriceAggregatorService` | `private final CoinGeckoClient cryptoClient`, `private final FinnhubClient stockClient`, `private final ForexClient forexClient` | [ ] |
| 3.9 | Add `priceCache` field | Field | In `PriceAggregatorService` | `private final PriceCache priceCache` | [ ] |
| 3.10 | Implement `aggregateCryptoPrices()` scheduled method | Method | In `PriceAggregatorService` | `@Scheduled(fixedRate = 15000)`, no params, calls `cryptoClient.fetchPrices()` | [ ] |
| 3.11 | Implement `aggregateStockPrices()` scheduled method | Method | In `PriceAggregatorService` | `@Scheduled(fixedRate = 15000)`, no params, calls `stockClient.fetchPrice()` per symbol | [ ] |
| 3.12 | Implement `aggregateForexRates()` scheduled method | Method | In `PriceAggregatorService` | `@Scheduled(fixedRate = 60000)`, no params, calls `forexClient.fetchRates()` | [ ] |
| 3.13 | Add `@EnableScheduling` annotation | Config | In `FinanceTickerApplication.java` | Or on `PriceAggregatorService` class | [ ] |

---

## Phase 4: WebSocket Server (Epic E4)

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 4.1 | Create `WebSocketConfig` class | Class | `config/WebSocketConfig.java` | `@Configuration`, `@EnableWebSocket` | [ ] |
| 4.2 | Implement `webSocketHandlerMapping()` bean method | Method | In `WebSocketConfig` | Returns `HandlerMapping`, maps `/ws/prices` to `TickerWebSocketHandler` | [ ] |
| 4.3 | Create `TickerWebSocketHandler` class | Class | `websocket/TickerWebSocketHandler.java` | `@Component`, extends `TextWebSocketHandler` | [ ] |
| 4.4 | Implement `afterConnectionEstablished()` method | Method | In `TickerWebSocketHandler` | Param: `WebSocketSession session`, sends initial prices, adds to subscription manager | [ ] |
| 4.5 | Implement `handleTextMessage()` method | Method | In `TickerWebSocketHandler` | Param: `WebSocketSession session`, `TextMessage message`, parses `SUBSCRIBE`/`UNSUBSCRIBE` | [ ] |
| 4.6 | Implement `afterConnectionClosed()` method | Method | In `TickerWebSocketHandler` | Param: `WebSocketSession session`, `CloseStatus status`, calls `cleanupSession()` | [ ] |
| 4.7 | Implement `sendPriceUpdate()` helper method | Method | In `TickerWebSocketHandler` | Params: `WebSocketSession session`, `TickerPrice price`, sends JSON message | [ ] |
| 4.8 | Create `SubscriptionManager` class | Class | `websocket/SubscriptionManager.java` | `@Component`, `@Singleton` | [ ] |
| 4.9 | Add `sessionSymbols` map field | Field | In `SubscriptionManager` | `private final ConcurrentHashMap<WebSocketSession, Set<String>> sessionSymbols` | [ ] |
| 4.10 | Implement `subscribe(WebSocketSession, String)` method | Method | In `SubscriptionManager` | Params: `WebSocketSession session`, `String symbol`, adds to session's set | [ ] |
| 4.11 | Implement `unsubscribe(WebSocketSession, String)` method | Method | In `SubscriptionManager` | Params: `WebSocketSession session`, `String symbol`, removes from session's set | [ ] |
| 4.12 | Implement `getSubscribedSessions(String symbol)` method | Method | In `SubscriptionManager` | Returns `Set<WebSocketSession>`, param: `String symbol` | [ ] |
| 4.13 | Implement `cleanupSession(WebSocketSession)` method | Method | In `SubscriptionManager` | Param: `WebSocketSession session`, removes from `sessionSymbols` | [ ] |
| 4.14 | Create `PriceUpdateEvent` listener class | Class | `event/PriceUpdateEvent.java` | Extends `ApplicationEvent`, field: `TickerPrice price` | [ ] |
| 4.15 | Implement `ApplicationListener<PriceUpdateEvent>` on handler | Interface | In `TickerWebSocketHandler` | Method: `onApplicationEvent(PriceUpdateEvent event)`, broadcasts to subscribed sessions | [ ] |

---

## Phase 5: Frontend Dashboard (Epic E5)

### Dashboard Layout

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 5.1.1 | Create `index.html` | File | `src/main/resources/static/index.html` | Elements: `<header>`, `<main id="grid">`, `<footer>`, search button, theme toggle | [ ] |
| 5.1.2 | Create `style.css` | File | `src/main/resources/static/style.css` | `:root` vars, `.card`, `.grid`, `.flash-green`, `.flash-red` | [ ] |
| 5.1.3 | Create `app.js` | File | `src/main/resources/static/app.js` | IIFE or module pattern, `DOMContentLoaded` init | [ ] |
| 5.1.4 | Implement `renderCard(ticker)` function | Function | In `app.js` | Param: `TickerPrice ticker`, returns DOM element | [ ] |
| 5.1.5 | Implement `updateCard(ticker)` function | Function | In `app.js` | Param: `TickerPrice ticker`, finds existing card, updates price/change, triggers flash | [ ] |
| 5.1.6 | Implement `removeCard(symbol)` function | Function | In `app.js` | Param: `string symbol`, removes card from DOM | [ ] |
| 5.1.7 | Add CSS Grid layout styles | CSS | In `style.css` | `display: grid`, `grid-template-columns: repeat(auto-fill, minmax(200px, 1fr))` | [ ] |
| 5.1.8 | Add card component styles | CSS | In `style.css` | `.card { padding, border-radius, shadow, transition }` | [ ] |
| 5.1.9 | Add `@keyframes flash-green` animation | CSS | In `style.css` | `0% { background-color: #4caf50 } → 100% { background-color: inherit }` | [ ] |
| 5.1.10 | Add `@keyframes flash-red` animation | CSS | In `style.css` | `0% { background-color: #f44336 } → 100% { background-color: inherit }` | [ ] |
| 5.1.11 | Add CSS custom properties (theme vars) | CSS | In `style.css` | `--bg-primary`, `--text-primary`, `--card-bg`, `--accent-green`, `--accent-red` | [ ] |

### Live Updates

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 5.2.1 | Create `WebSocketClient` class | Class | In `app.js` | Class with `ws` field, `reconnectAttempts` counter | [ ] |
| 5.2.2 | Implement `connect()` method | Method | In `WebSocketClient` | No params, connects to `ws://localhost:8080/ws/prices`, sets up handlers | [ ] |
| 5.2.3 | Implement `send(message)` method | Method | In `WebSocketClient` | Param: `object message`, calls `ws.send(JSON.stringify(message))` | [ ] |
| 5.2.4 | Implement `onMessage(handler)` method | Method | In `WebSocketClient` | Param: `function handler`, sets `ws.onmessage` | [ ] |
| 5.2.5 | Implement `onOpen(handler)` method | Method | In `WebSocketClient` | Param: `function handler`, sets `ws.onopen` | [ ] |
| 5.2.6 | Implement `onClose(handler)` method | Method | In `WebSocketClient` | Param: `function handler`, sets `ws.onclose` | [ ] |

---

## Phase 6: Watchlist & Search (Epic E6)

### Watchlist Persistence

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 6.1.1 | Create `watchlist.js` module | File | `src/main/resources/static/watchlist.js` | IIFE or ES module, uses `localStorage` | [ ] |
| 6.1.2 | Implement `load()` function | Function | In `watchlist.js` | Returns `string[]`, reads from `localStorage.getItem('watchlist')` | [ ] |
| 6.1.3 | Implement `save(symbols)` function | Function | In `watchlist.js` | Param: `string[] symbols`, writes to `localStorage.setItem('watchlist', ...)` | [ ] |
| 6.1.4 | Implement `add(symbol)` function | Function | In `watchlist.js` | Param: `string symbol`, pushes to array, calls `save()` | [ ] |
| 6.1.5 | Implement `remove(symbol)` function | Function | In `watchlist.js` | Param: `string symbol`, filters array, calls `save()` | [ ] |
| 6.1.6 | Implement `contains(symbol)` function | Function | In `watchlist.js` | Returns `boolean`, param: `string symbol`, calls `array.includes()` | [ ] |

### Symbol Search

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 6.2.1 | Create `SearchController` class | Class | `controller/SearchController.java` | `@RestController`, `@RequestMapping("/api/search")` | [ ] |
| 6.2.2 | Implement `search(String query)` GET endpoint | Method | In `SearchController` | Returns `List<SymbolInfo>`, param: `@RequestParam String q`, endpoint: `GET /search?q=...` | [ ] |
| 6.2.3 | Create `symbols.json` data file | File | `src/main/resources/data/symbols.json` | Array of `{ "symbol": "AAPL", "name": "Apple Inc.", "assetClass": "STOCK" }` | [ ] |
| 6.2.4 | Create `SymbolInfo` record | Record | `model/SymbolInfo.java` | Fields: `String symbol`, `String name`, `String assetClass`, `String description` | [ ] |
| 6.2.5 | Create `search.js` module | File | `src/main/resources/static/search.js` | ES module, `fetch` API for calling backend | [ ] |
| 6.2.6 | Implement `debouncedSearch(query)` function | Function | In `search.js` | Param: `string query`, returns `Promise<List<SymbolInfo>>`, 300ms debounce | [ ] |
| 6.2.7 | Implement `renderSearchResults(results)` function | Function | In `search.js` | Param: `SymbolInfo[] results`, renders clickable list items | [ ] |
| 6.2.8 | Create search modal HTML | HTML | In `index.html` | `<dialog>` or `<div class="modal">`, input field, results container | [ ] |

---

## Phase 7: Resilience (Epic E7)

### API Failure Recovery

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 7.1.1 | Create `CircuitBreaker` class | Class | `resilience/CircuitBreaker.java` | Fields: `failureThreshold`, `resetTimeoutMs`, `currentState`, `failureCount`, `lastFailureTime` | [ ] |
| 7.1.2 | Create `CircuitState` enum | Enum | `resilience/CircuitState.java` | Values: `CLOSED`, `OPEN`, `HALF_OPEN` | [ ] |
| 7.1.3 | Implement `recordSuccess()` method | Method | In `CircuitBreaker` | No params, resets `failureCount=0`, sets `state=CLOSED` | [ ] |
| 7.1.4 | Implement `recordFailure()` method | Method | In `CircuitBreaker` | No params, increments `failureCount`, opens circuit if threshold exceeded | [ ] |
| 7.1.5 | Implement `allowRequest()` method | Method | In `CircuitBreaker` | Returns `boolean`, checks if `state=CLOSED` or `HALF_OPEN` with timeout elapsed | [ ] |
| 7.1.6 | Add `circuitBreaker` field to each API client | Field | In `CoinGeckoClient`, `FinnhubClient`, `ForexClient` | `private final CircuitBreaker circuitBreaker` | [ ] |
| 7.1.7 | Wrap API calls with circuit breaker check | Logic | In each client's fetch method | `if (!circuitBreaker.allowRequest()) return cached/stale data` | [ ] |
| 7.1.8 | Add `isStale` field to `TickerPrice` | Field | In `TickerPrice.java` | `boolean isStale` or `Instant staleAfter` | [ ] |
| 7.1.9 | Add stale badge CSS styles | CSS | In `style.css` | `.stale-badge { opacity, grayscale filter }` | [ ] |
| 7.1.10 | Implement stale badge rendering logic | Function | In `app.js` | Checks `price.age > 30s`, adds `.stale` class to card | [ ] |

### WebSocket Auto-Reconnect

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 7.2.1 | Create `ReconnectingWebSocket` wrapper | Class | In `app.js` | Fields: `ws`, `url`, `reconnectAttempts`, `maxReconnectAttempts`, `backoffMs` | [ ] |
| 7.2.2 | Implement exponential backoff logic | Logic | In `ReconnectingWebSocket` | `delay = min(backoffMs * 2^attempts, 30000)` | [ ] |
| 7.2.3 | Implement `reconnect()` method | Method | In `ReconnectingWebSocket` | No params, calls `connect()` after backoff delay | [ ] |
| 7.2.4 | Add re-subscribe logic on reconnect | Logic | In `ReconnectingWebSocket` | On `onopen`, resends `SUBSCRIBE` for all symbols in watchlist | [ ] |

---

## Phase 8: Polish & Deployment (Epic E8)

### Connection Status

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 8.1.1 | Add status indicator element to HTML | HTML | In `index.html` footer | `<span id="status" class="status-indicator">● Connected</span>` | [ ] |
| 8.1.2 | Add status indicator CSS styles | CSS | In `style.css` | `.status-indicator`, `.status-connected`, `.status-disconnected`, `.status-reconnecting` | [ ] |
| 8.1.3 | Implement `updateStatus(state)` function | Function | In `app.js` | Param: `'connected' | 'disconnected' | 'reconnecting'`, updates text and class | [ ] |

### Theme Toggle

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 8.2.1 | Add theme toggle button to HTML | HTML | In `index.html` header | `<button id="theme-toggle">🌙/☀️</button>` | [ ] |
| 8.2.2 | Add dark theme CSS custom properties | CSS | In `style.css` | `body.dark-theme { --bg-primary, --text-primary, ... }` | [ ] |
| 8.2.3 | Implement `setTheme(theme)` function | Function | In `app.js` | Param: `'dark' | 'light'`, sets `document.body.classList` and saves to `localStorage` | [ ] |
| 8.2.4 | Implement `toggleTheme()` function | Function | In `app.js` | No params, swaps theme, calls `setTheme()` | [ ] |

### REST Endpoint

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 8.3.1 | Create `PriceController` class | Class | `controller/PriceController.java` | `@RestController`, `@RequestMapping("/api/prices")`, fields: `private final PriceCache priceCache` | [ ] |
| 8.3.2 | Implement `getPrices(Set<String> symbols)` GET endpoint | Method | In `PriceController` | Returns `List<TickerPrice>`, param: `@RequestParam Set<String> symbols`, endpoint: `GET /prices?symbols=AAPL,BTC` | [ ] |

### Documentation & Deployment

| ID | Task | Type | Location | Fields/Parameters | Status |
|----|------|------|----------|-------------------|--------|
| 8.4.1 | Write `README.md` | File | Root `README.md` | Sections: Overview, Quick Start, API Key Config, Architecture, Endpoints | [ ] |
| 8.4.2 | Finalize `Dockerfile` | File | Root `Dockerfile` | `FROM eclipse-temurin:17-jre-alpine`, `COPY target/*.jar app.jar`, `ENTRYPOINT ["java","-jar","/app.jar"]` | [ ] |
| 8.4.3 | Finalize `docker-compose.yml` | File | Root `docker-compose.yml` | `services.app.build`, `services.app.ports: ["8080:8080"]`, `services.app.environment` | [ ] |

---

## Task Summary

| Phase | Tasks | Completed | Remaining |
|-------|-------|-----------|-----------|
| Phase 1: Foundation | 7 | 0 | 7 |
| Phase 2: API Clients | 24 | 0 | 24 |
| Phase 3: Aggregation | 13 | 0 | 13 |
| Phase 4: WebSocket | 15 | 0 | 15 |
| Phase 5: Frontend UI | 17 | 0 | 17 |
| Phase 6: Watchlist/Search | 14 | 0 | 14 |
| Phase 7: Resilience | 14 | 0 | 14 |
| Phase 8: Polish | 13 | 0 | 13 |
| **Total** | **117** | **0** | **117** |

---

## Recommended Order of Execution

1. **Phase 1** - Foundation (tasks 1.1-1.7)
2. **Phase 2.4** - HTTP Client Config (tasks 2.4.1-2.4.3)
3. **Phase 2.1** - CoinGecko Client (tasks 2.1.1-2.1.7)
4. **Phase 3** - Price Cache & Aggregator (tasks 3.1-3.13)
5. **Phase 2.2** - Finnhub Client (tasks 2.2.1-2.2.8)
6. **Phase 2.3** - Forex Client (tasks 2.3.1-2.3.7)
7. **Phase 4** - WebSocket Server (tasks 4.1-4.15)
8. **Phase 5** - Frontend Dashboard (tasks 5.1.1-5.2.6)
9. **Phase 6** - Watchlist & Search (tasks 6.1.1-6.2.8)
10. **Phase 7** - Resilience (tasks 7.1.1-7.2.4)
11. **Phase 8** - Polish & Deploy (tasks 8.1.1-8.4.3)

---

## Notes

- Update `[ ]` to `[x]` when completing tasks
- Tasks are broken down to atomic units (single class/file/method)
- Dependencies flow top-to-bottom within each phase

---

## Extension: Target Directory Structure

```
FinanceTicker/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── kapur/FinanceTicker/
│   │   │       ├── FinanceTickerApplication.java    # Entry point
│   │   │       │
│   │   │       ├── model/                           # Task 1.2-1.5, 6.2.4
│   │   │       │   ├── TickerPrice.java
│   │   │       │   ├── AssetClass.java
│   │   │       │   ├── WebSocketMessage.java
│   │   │       │   ├── MessageType.java
│   │   │       │   └── SymbolInfo.java
│   │   │       │
│   │   │       ├── config/                          # Task 2.4.1, 4.1
│   │   │       │   ├── HttpClientConfig.java
│   │   │       │   └── WebSocketConfig.java
│   │   │       │
│   │   │       ├── client/                          # Task 2.1-2.3
│   │   │       │   ├── CoinGeckoClient.java
│   │   │       │   ├── FinnhubClient.java
│   │   │       │   ├── ForexClient.java
│   │   │       │   │
│   │   │       │   └── dto/                         # Task 2.1.7, 2.2.8, 2.3.7
│   │   │       │       ├── CoinGeckoPriceResponse.java
│   │   │       │       ├── FinnhubQuoteResponse.java
│   │   │       │       └── ExchangeRateResponse.java
│   │   │       │
│   │   │       ├── service/                         # Task 3.1, 3.7
│   │   │       │   ├── PriceCache.java
│   │   │       │   └── PriceAggregatorService.java
│   │   │       │
│   │   │       ├── websocket/                       # Task 4.3, 4.8
│   │   │       │   ├── TickerWebSocketHandler.java
│   │   │       │   └── SubscriptionManager.java
│   │   │       │
│   │   │       ├── event/                           # Task 4.14
│   │   │       │   └── PriceUpdateEvent.java
│   │   │       │
│   │   │       ├── controller/                      # Task 6.2.1, 8.3.1
│   │   │       │   ├── SearchController.java
│   │   │       │   └── PriceController.java
│   │   │       │
│   │   │       └── resilience/                      # Task 7.1.1-7.1.2
│   │   │           ├── CircuitBreaker.java
│   │   │           └── CircuitState.java
│   │   │
│   │   └── resources/
│   │       ├── application.yml                      # Task 1.1
│   │       │
│   │       ├── static/                              # Task 5.1, 6.1, 6.2
│   │       │   ├── index.html
│   │       │   ├── style.css
│   │       │   ├── app.js
│   │       │   ├── watchlist.js
│   │       │   └── search.js
│   │       │
│   │       └── data/                                # Task 6.2.3
│   │           └── symbols.json
│   │
│   └── test/
│       └── java/kapur/FinanceTicker/
│           ├── FinanceTickerApplicationTests.java
│           │
│           └── client/                              # Future tests
│               ├── CoinGeckoClientTest.java
│               └── FinnhubClientTest.java
│
├── Dockerfile                                       # Task 1.6
├── docker-compose.yml                               # Task 1.7
├── pom.xml                                          # Already exists
├── README.md                                        # Task 8.4.1
│
└── docs/
    ├── board.md                                     # Already exists
    ├── PRD_Finance_Ticker_WebApp.md
    └── Implementation_Plan_Finance_Ticker.md
```

### Package Purposes

| Package | Purpose |
|---------|---------|
| `model` | Core data types (records, enums) used everywhere |
| `config` | Spring `@Configuration` classes that create beans |
| `client` | API clients that fetch data from external services |
| `client.dto` | Internal DTOs matching external API response shapes |
| `service` | Business logic (aggregation, caching, scheduling) |
| `websocket` | WebSocket handling and subscription tracking |
| `event` | Spring events for pub/sub between components |
| `controller` | REST API endpoints (`@RestController`) |
| `resilience` | Circuit breaker logic for fault tolerance |
| `static` | Frontend files (HTML/CSS/JS) served directly by Spring Boot |