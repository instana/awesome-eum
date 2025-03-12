namespace MauiSample;

#if IOS || MACCATALYST
using InstanaBinding = NewBindingMaciOS.DotnetNewBinding;
#elif ANDROID
using InstanaBinding = NewBindingAndroid.DotnetNewBinding;
using Android.Content;
using Java.Lang;
#elif (NETSTANDARD || !PLATFORM) || (NET6_0_OR_GREATER && !IOS && !ANDROID)
using InstanaBinding = System.Object;
#endif

public partial class MainPage : ContentPage
{
	public MainPage()
	{
		InitializeComponent();
		// Call the native binding, which will append a platform specific string to the input string
		var labelText = InstanaBinding.GetString("Community Toolkit");
		#if IOS
		InstanaBinding.Setup("KEY","URL");
		#endif
		#if ANDROID
		InstanaBinding.Setup(Platform.CurrentActivity,"KEY","URL");
		#endif
		newBindingSampleLabel.Text = "Hello, " + labelText;

		imageFromUrl.Source = "https://www.example.com/sample-image.jpg";
		InstanaBinding.SetView("View name");
		InstanaBinding.SetUserName("User Name");
		InstanaBinding.SetUserId("UserId");
		InstanaBinding.SetCustomEvent("Custom event name");
		InstanaBinding.SetUserEmail("test@test.com");
		InstanaBinding.SetView("View name 1");

	}

	async void OnDocsButtonClicked(object sender, EventArgs e)
	{
		try
		{
			Uri uri = new Uri("https://learn.microsoft.com/dotnet/communitytoolkit/maui/native-library-interop/get-started");
			await Browser.Default.OpenAsync(uri, BrowserLaunchMode.SystemPreferred);
		}
		catch (Exception ex)
		{
			throw new Exception("Browser failed to launch", ex);
		}
	}
}

