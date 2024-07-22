# JEST

[JEST](https://jestjs.io/docs/api){:target="_blank"} is integrated into synthetic browser playback engine. Jest [global APIs](https://jestjs.io/docs/api){:target="_blank"} are available for use, like `describe`, `test`, `beforeAll` etc. The [expect](https://jestjs.io/docs/expect){:target="_blank"} API also gives you access to a number of "matchers" that let you validate different things.

To enable JEST support, either start command line with `--jest` flag, or use `integration: { type: "jest" }` as parameter of `executeSyntheticTest` if import `@console/synthetics-browserscript-playback` as module.

## Subscribe JEST Events

Jest events could be retrieved from callback `onReport.onEvent`

Potential jest events could be

- `start_describe_definition`
- `finish_describe_definition`
- `add_hook`
- `add_test`
- `error`
- `setup`
- `include_test_location_in_result`
- `hook_start`
- `hook_success`
- `hook_failure`
- `test_fn_start`
- `test_fn_success`
- `test_fn_failure`
- `test_retry`
- `test_start`
- `test_skip`
- `test_todo`
- `test_done`
- `run_describe_start`
- `run_describe_finish`
- `run_start`
- `run_finish`
- `teardown`

Sample codes to observe events.

```javascript
const runner = require("@console/synthetics-browserscript-playback");

runner.executeSyntheticTest({
  // ... ...
  integration: { type: "jest" },
  onReport: {
    onEvent: (data) => {
      if (data.name === "jest_event") {
        console.log(
          data.name,
          data.param.name,
          data.param.timestamp,
          JSON.stringify(data.param)
        );
      }
    },
    // ... ...
  },
  // ... ...
});
```
