int ledPin = 5;

void setup() {
  Serial.begin(9600);
  pinMode(ledPin, OUTPUT);
}

void loop() {
  while(Serial.available() > 0) {
    char i = Serial.read();
    Serial.write(i);
    if (i == '0'){
      digitalWrite(ledPin, LOW);
      digitalWrite(LED_BUILTIN, LOW);
    } else if (i == '1'){
      digitalWrite(ledPin, HIGH);
      digitalWrite(LED_BUILTIN, HIGH);
    }
  }
}
