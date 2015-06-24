<div class="slider-parent">
    <div class="auth-slider" id="slider">
        <li class="slide-auth">
            <div>
                <div class="auth-form">
                    <input class="auth-loginfield authform-field" placeholder="email or phone"/>
                    <input class="auth-passfield authform-field" type="password" placeholder="password"/>
                    <div class="auth-login auth-button">Sign in</div>
                    <div class="auth-register auth-button">Register</div>
                </div>
            </div>
        </li>
        <li class="slide-registration1">
            <div>
                <div class="auth-form">
                    <input class="register-emailfield authform-field" placeholder="email"/>
                    <input class="register-phonefield authform-field" placeholder="phone"/>
                    <input class="register-passfield authform-field" type="password" placeholder="password"/>
                    <div class="auth-backlogin auth-button">Sign in</div>
                    <div class="auth-continueregister auth-button">Register</div>
                </div>
            </div>
        </li>
        <li class="slide-registration2">
            <div>
                <div class="auth-form">
                    <input class="register-name authform-field" placeholder="Name"/>
                    <input class="register-surname authform-field" placeholder="Surname"/>
                    <div class="auth-backlogin auth-button">Sign in</div>
                    <div class="auth-fullregister auth-button">Register</div>
                </div>
            </div>
        </li>
    </div>
</div>
<script>
    $(document).ready(function() {
        
        $('#slider').rhinoslider({
            easing: 'easeInOutQuart',
            effectTime: 500,
            controlsMousewheel: false,
            controlsKeyboard: false,
            controlsPrevNext: false,
            controlsPlayPause: false,
            animateActive: false,
            showBullets: 'never',
            showControls: 'never'
        });
        $(".auth-login").click(function() {StudWorld.auth($(".auth-loginfield").val(), "", $(".auth-passfield").val());});
        $(".auth-backlogin").click(function() {$('#slider').data('rhinoslider').next($('.auth-form'));});
        $(".auth-register").click(function() {$('#slider').data('rhinoslider').next($('.slide-registration1'));});
        $(".auth-continueregister").click(function() {$('#slider').data('rhinoslider').next($('.slide-registration2'));});
        $(".auth-fullregister").click(function() {StudWorld.register($(".register-emailfield").val(), $(".register-phonefield").val(), $(".register-passfield").val(), $(".register-name").val(), $(".register-surname").val());});
    });
</script>