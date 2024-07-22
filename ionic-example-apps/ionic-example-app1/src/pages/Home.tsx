import { IonButtons, IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle, IonContent, IonHeader, IonMenuButton, IonPage, IonText, IonTitle, IonToolbar } from '@ionic/react';

const Home = () => {

	return (
		<IonPage>
			<IonHeader>
				<IonToolbar>
					<IonButtons slot="start">
						<IonMenuButton />
					</IonButtons>
					<IonTitle>Home</IonTitle>
				</IonToolbar>
			</IonHeader>

			<IonContent fullscreen>
				<IonHeader collapse="condense">
					<IonToolbar>
						<IonTitle size="large">Home</IonTitle>
					</IonToolbar>
				</IonHeader>

				<IonCard>
					<IonCardHeader>
						<IonCardSubtitle>EUM monitoring example</IonCardSubtitle>
						<IonCardTitle>Welcome Home!</IonCardTitle>
					</IonCardHeader>

					<IonCardContent>
						<IonText>
							<p>
								1. Click on each of item of the navbar to record page transitions using page api.<br />
                                2. Go to Errors Tab to checkout Error reporting.<br />
                                3. Go to XHR Tab for reporting Xml Http Requests.<br />
                                4. Go to Custom Events Tab to explore Custom Events reporting. <br />
								5. Checkout README to enable backend tracing. <br />
								6. To Enable autoPageDetection using api use agent version 1.7.1 and above. <br />
							</p>
						</IonText>
						<br />
						<IonText>
							<p>
								Website monitoring docs <a href="https://www.ibm.com/docs/en/instana-observability/272?topic=instana-monitoring-websites" target="_blank" rel="noreferrer">here.</a>
							</p>
						</IonText>
					</IonCardContent>
				</IonCard>
			</IonContent>
		</IonPage>
	);
};

export default Home;