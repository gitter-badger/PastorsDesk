// Copyright (c) ImaBrokeDude - Licensed under GNU GPL
// For more information, see license file in the main folder

namespace PastorsDesk.Shared.Network
{
    public static class Op
    {
        public const int Login = 0x02;
        public const int LoginR = 0x04;

        public const int Reconnect = 0x10;
        public const int ReconnectR = 0x12;

        public const int ClientIdent = 0x0FD10100;
        public const int ClientIdentR = 0x1F;

    }
}
