# IBM-Instana-Demo

A demo application for understanding the features & capabilities of IBM Instana Flutter Agent.

## Features Identified

- ***Runtime update of reporting URL & Instana Key***: Dynamically configure the reporting URL and Instana key within the application at runtime, allowing flexibility and easy management.

- ***Create/Update User Data - mocking user login***: Simulate user login to update user data and ensure accurate tracking of identified users. The information provided will be reflected in the identified user list.

- ***Update Instana configuration in runtime***: Update the Instana configuration on-the-fly, including the reporting URL and key, without requiring a restart. This allows for seamless adjustments to the Instana integration.

- ***Make network calls based on the identified Network client***: Instana Flutter Agent make network calls and monitor network performance. Test and analyze your app's behavior under various network scenarios with custom urls.

- ***Add View functionality to identify the pages/views visited by the user***: Implement view tracking to gain insights into the user's navigation patterns within the app. Monitor and analyze which pages or views are most frequently visited by users.

- ***Custom events reporting***: Generate and report custom events within the app. Associate custom event names with metadata to provide meaningful insights in the Instana dashboard. Track specific user actions or milestones using custom event reporting.

- ***Run All At Once***: This feature enables testing all the aforementioned features with a single click. At the end of the process, we will obtain the expected result set with timestamp, which we can review on the Instana dashboard to confirm if everything is functioning properly or not.

> **Note:** These features are designed to showcase the capabilities of IBM Instana Flutter Agent and provide a comprehensive understanding of its functionality.

For more information and detailed documentation, visit the [IBM Instana](https://www.ibm.com/products/instana) official website. Additionally, you can refer to the [Flutter Agent API documentation](https://www.ibm.com/docs/en/instana-observability/current?topic=monitoring-flutter-api) for detailed information about the Instana Flutter Agent API.

🎉 **Get started with IBM Instana and explore the power of real-time monitoring and analytics in your Flutter applications!**


<details>
  <summary>
        <table style="margin-left: auto; margin-right: auto; ">
            <tr>
                <td align="center">
                    <img src="assets/images/crop.png" width="40" height = "40"/>
                </td>
                <td align="center">
                    Screen Captures
                </td>
          </tr>
        </table>
  </summary>
  </br>
  <div style="overflow-x: auto;">
    <table style="margin-left: auto; margin-right: auto; ">
      <tr>
        <td align="center">
          <b><p align="center">Credentials Screen - onetime</p></b>
        </td>
        <td align="center">
          <b><p align="center">Feature Listing Screen</p></b>
        </td>
        <td align="center">
          <b><p align="center">User Data Screen</p></b>
        </td>
        <td align="center">
          <b><p align="center">Network Request Screen</p></b>
        </td>
        <td align="center">
          <b><p align="center">Run All At Once Result</p></b>
        </td>
      </tr>
      <tr>
        <td align="center">
          <img src="assets/images/credential_screen.png" alt="Screenshot 5" height="300" width="250"/>
        </td>
        <td align="center">
          <img src="assets/images/feature_listing.png" alt="Screenshot 5" height="300" width="250"/>
        </td>
        <td align="center">
          <img src="assets/images/profile_screen.png" alt="Screenshot 3" height="300" width="250"/>
        </td>
        <td align="center">
          <img src="assets/images/network_screen.png" alt="Screenshot 4" height="300" width="250"/>
        </td>
        <td align="center">
          <img src="assets/images/all_at_once_screen.png" alt="Screenshot 5" height="300" width="200"/>
        </td>
      </tr>
    </table>
  </div>

</details>