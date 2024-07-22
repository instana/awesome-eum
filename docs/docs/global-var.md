# Synthetics Browser Script Playback API

The synthetics browser script playback is based on Selenium Webdriver, extra functionalities are added to facilitate the development of synthetic tests.

## Global variables

| Name     | Type                        | Details                                                                                                         |
| -------- | --------------------------- | --------------------------------------------------------------------------------------------------------------- |
| $driver  | `@types/selenium-webdriver` | Type definitions for Selenium WebDriverJS                                                                       |
| $browser | `ThenableWebDriver`         | A thenable wrapper that allows call `WebDriver` methods directly on `Promise<WebDriver>` without calling `then` |
| $bro     | `WebDriver`                 | A WebDriver client, which provides control over a browser                                                       |
| $env     | `Dict<string>`              | Environment variables                                                                                           |
| $secure  | `Dict<string>`              | Secrets from external security vault                                                                            |
| $util    | [`UserUtil`](#userinsight)  | Utility functions                                                                                               |

## Type definitions

### UserInsight

```typescript
export declare class UserUtil {
  insights: UserInsight;
  is: {
    undefined: (ref: any) => boolean;
    defined: (ref: any) => boolean;
    NaN: (ref: any) => boolean;
    number: (ref: any) => boolean;
    numeric: (ref: any) => boolean;
    string: (ref: any) => boolean;
    array: (ref: any) => boolean;
    object: (ref: any) => boolean;
    function: (ref: any) => boolean;
    collection: (ref: any) => boolean;
    empty: (ref: any) => boolean;
  };
}

export declare class UserInsight {
  set: (k: string, v: any) => Map<string, any>;
  get: (k: string) => any;
  getKeys: () => () => IterableIterator<string>;
  has: (k: string) => boolean;
  unset: (k: string) => boolean;
  unsetAll: () => void;
}
```
