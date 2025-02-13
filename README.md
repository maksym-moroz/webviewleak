This repository contains a very simple app that reproduces a critical memory leak in Chromium Webview present in AOSP build API 32/33.

Here's a sample activity that reproduces the leak:

```
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity)

    val webView = findViewById<WebView>(R.id.webview)

    findViewById<Button>(R.id.recreate).setOnClickListener {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }
  }
}
```

Activity will be held from being garbage collected due to the fact it's referenced in a static HashMap inside AwWindowCoverageTracker.java via HashMap key of type DecorView.
```
@VisibleForTesting
public static final Map<View, AwWindowCoverageTracker> sWindowCoverageTrackers = new HashMap<>();

```
