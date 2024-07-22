import { IonButton, IonButtons, IonContent, IonHeader, IonMenuButton, IonPage, IonTitle, IonToolbar } from '@ionic/react';

const XHR = () => {

	return (
		<IonPage>
			<IonHeader>
				<IonToolbar>
					<IonButtons slot="start">
						<IonMenuButton />
					</IonButtons>
					<IonTitle>Create Custom Events</IonTitle>
				</IonToolbar>
			</IonHeader>

			<IonContent fullscreen>
				<IonHeader collapse="condense">
					<IonToolbar>
						<IonTitle size="large">Custom Events</IonTitle>
					</IonToolbar>
				</IonHeader>

				
				<IonButton className="button-margin" expand="block" onClick={ //@ts-ignore
				ineum('reportEvent', 'Custom event button clicked')}>
					Send custom event
				</IonButton>
				<IonButton className="button-margin" expand="block" onClick={//@ts-ignore
				ineum('reportEvent', 'Send custom metric', {customMetric: 123.124})}>
					Send custom metric
				</IonButton>
			</IonContent>
		</IonPage>
	);
};

export default XHR;