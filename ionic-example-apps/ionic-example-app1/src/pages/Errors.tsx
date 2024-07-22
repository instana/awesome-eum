import { IonButton, IonButtons, IonContent, IonHeader, IonMenuButton, IonPage, IonText, IonTitle, IonToolbar, useIonActionSheet } from '@ionic/react';

import { useState } from 'react';

const Errors = () => {

	const [ present, dismiss ] = useIonActionSheet();
    const [hasError, setHasError] = useState(false);
    const errorMessage = 'This is a controlled error triggered by the button.';

    const handleButtonClick = () => {
        throw new Error('Another error type.');
    };
 
    function throwAnError() {
        throw new Error("This is intended for testing purposes.");
    }

    const handleManyButtonClick = () => {
        setTimeout(throwAnError, 10);
        setTimeout(throwAnError, 10);
        setTimeout(throwAnError, 10);
        throw new Error('Another error type.');
    };

	return (
		<IonPage>
			<IonHeader>
				<IonToolbar>
					<IonButtons slot="start">
						<IonMenuButton />
					</IonButtons>
					<IonTitle>Monitor Errors</IonTitle>
				</IonToolbar>
			</IonHeader>

			<IonContent fullscreen>
				<IonHeader collapse="condense">
					<IonToolbar>
						<IonTitle size="large">Errors</IonTitle>
					</IonToolbar>
				</IonHeader>
				<IonButton
					expand="block"
					onClick={handleButtonClick}
				>
					Create Error
				</IonButton>
				<IonButton
					expand="block"
					onClick={handleManyButtonClick}
				>
					Create Many Errors
				</IonButton>
			</IonContent>
		</IonPage>
	);
};

export default Errors;