package com.example.animezevki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // SharedPreferences ile ilk giriş durumunu kontrol etme
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean hasAcceptedTerms = preferences.getBoolean("hasAcceptedTerms", false);

        if (hasAcceptedTerms) {
            // Eğer kullanıcı şartları daha önce kabul ettiyse, WelcomeActivity'e yönlendir
            startActivity(new Intent(this, WelcomeActivity.class));
            finish(); // TermsActivity'yi kapat
            return;
        }

        // activity_terms.xml dosyasını belirtilen içeriği kullanarak görüntüleme
        setContentView(R.layout.activity_terms);

        // XML dosyasındaki öğeleri bulma
        CheckBox termsOfUseCheckbox = findViewById(R.id.terms_of_use_checkbox);
        CheckBox termsOfServiceCheckbox = findViewById(R.id.terms_of_service_checkbox);
        Button continueButton = findViewById(R.id.continue_button);
        TextView termsAndConditionsText = findViewById(R.id.terms_and_conditions_text);

        // Metni gösterme
        termsAndConditionsText.setText(getString(R.string.terms_and_conditions));

        // Devam Et düğmesini başlangıçta devre dışı bırakma
        continueButton.setEnabled(false);

        // CheckBox'lar için tıklama dinleyicilerini ayarlama
        termsOfUseCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContinueButtonState(termsOfUseCheckbox.isChecked(), termsOfServiceCheckbox.isChecked(), continueButton);
            }
        });

        termsOfServiceCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContinueButtonState(termsOfUseCheckbox.isChecked(), termsOfServiceCheckbox.isChecked(), continueButton);
            }
        });

        // Devam Et düğmesine tıklama dinleyicisi ekleme
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Devam Et butonuna tıklandığında activity_welcome.xml ekranını aç
                openWelcomeActivity();

                // Kullanıcı şartları kabul ettiğini SharedPreferences'e kaydet
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("hasAcceptedTerms", true);
                editor.apply();
            }
        });


        // strings.xml yerine metni buraya yazdım daha mantıklı geldi bana
        String metin = "Hoş geldiniz! SiteAdı uygulamamızı yüklediğiniz için teşekkür ederiz. Lütfen aşağıdaki kullanım koşulları ve hizmet şartlarını dikkatlice okuyunuz. Bu şartlar, uygulamamızı kullanarak oluşturduğumuz bir işbirliğini düzenler ve sizin için en iyi deneyimi sağlamak amacıyla oluşturulmuştur.\n\n" +
                "1. Gizlilik ve Güvenlik\n\n" +
                "SiteAdı, sizin ve verilerinizin güvenliği konusuna büyük önem verir. Kişisel bilgileriniz sadece uygulamanın doğru şekilde çalışabilmesi ve size daha iyi hizmet sunabilmesi için kullanılır. Bu bilgiler asla üçüncü taraflarla paylaşılmaz veya satılmaz.\n\n" +
                "2. Kullanıcı Sorumluluğu\n\n" +
                "SiteAdı uygulamasının kullanımı tamamen sizin kontrolünüzdedir. Uygulamanın doğru ve güvenli bir şekilde kullanılması için gereken önlemleri almak sizin sorumluluğunuzdadır.\n\n" +
                "3. Adil Kullanım Politikası\n\n" +
                "SiteAdı kullanıcılarından, platformu hukuka aykırı bir şekilde kullanmamalarını rica eder. Uygunsuz içeriklerin paylaşımı, diğer kullanıcıların haklarını ihlal etmek ve yasa dışı faaliyetlere teşvik etmek kesinlikle yasaktır.\n\n" +
                "4. İşlevsellik ve Güncellemeler\n\n" +
                "SiteAdı sürekli olarak güncellenir ve yeni özellikler eklenir. Bu güncellemeleri düzenli olarak kontrol etmeniz, en iyi kullanıcı deneyimini yaşamanızı sağlar.\n\n" +
                "5. Yardım ve Destek\n\n" +
                "Herhangi bir sorunuz veya yardıma ihtiyacınız varsa, destek@siteniz.com üzerinden bizimle iletişime geçebilirsiniz. Ekibimiz size yardımcı olmaktan mutluluk duyacaktır.\n\n" +
                "6. Değişiklikler ve Bildirimler\n\n" +
                "Bu kullanım koşulları zaman zaman güncellenebilir. Önemli değişiklikler hakkında sizi uygulama içi bildirim veya kayıtlı e-posta aracılığıyla bilgilendireceğiz.";

        // burda textviewi gösterip içine oluşturduğum değişkeni atıyorum.
        TextView termsAndConditionsTextView = findViewById(R.id.terms_and_conditions_text);
        termsAndConditionsTextView.setText(metin);

    }

    // Devam Et butonunu duruma bağlı olarak güncelleme
    private void updateContinueButtonState(boolean termsOfUseChecked, boolean termsOfServiceChecked, Button continueButton) {
        continueButton.setEnabled(termsOfUseChecked && termsOfServiceChecked);
    }

    // WelcomeActivity'e yönlendirme
    private void openWelcomeActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        // Eğer TermsActivity ekranını kapatmak istiyorsanız aşağıdaki satırı ekleyebilirsiniz
        // finish();
    }
}
