package com.aoya.televip.hooks

import com.aoya.televip.utils.Hook
import com.aoya.televip.utils.HookStage
import com.aoya.televip.utils.hook
import de.robv.android.xposed.XposedHelpers.setObjectField
import com.aoya.televip.core.obfuscate.ResolverManager as resolver

class HidePhoneNumber :
    Hook(
        "hide_phone_number",
        "Hide 'Phone' number",
    ) {
    override fun init() {
        findClass(
            "org.telegram.messenger.UserConfig",
        ).hook(resolver.getMethod("org.telegram.messenger.UserConfig", "getCurrentUser"), HookStage.AFTER) { param ->
            val user = param.getResult() ?: return@hook
            setObjectField(user, resolver.getField("org.telegram.tgnet.TLRPC.User", "phone"), null)
        }
    }
}
