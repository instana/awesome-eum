import { IonButton, IonButtons, IonContent, IonHeader, IonMenuButton, IonPage, IonTitle, IonToolbar } from '@ionic/react';

const XHR = () => {

	return (
		<IonPage>
			<IonHeader>
				<IonToolbar>
					<IonButtons slot="start">
						<IonMenuButton />
					</IonButtons>
					<IonTitle>Send XMLHttpRequests</IonTitle>
				</IonToolbar>
			</IonHeader>

			<IonContent fullscreen>
				<IonHeader collapse="condense">
					<IonToolbar>
						<IonTitle size="large">XHR</IonTitle>
					</IonToolbar>
				</IonHeader>

				<IonButton expand='block' onClick={async () => {await fetch("https://fakestoreapi.com/products");}}>
					Send api call
				</IonButton>
			</IonContent>
		</IonPage>
	);
};

export default XHR;