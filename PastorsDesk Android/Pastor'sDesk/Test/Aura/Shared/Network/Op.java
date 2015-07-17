//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network;


// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* All Op codes
*/
public class Op   
{
    // Login Server
    // ------------------------------------------------------------------
    public static final int ClientIdent = 0x0FD1020A;
    public static final int ClientIdentR = 0x1F;
    public static final int Login = 0x0FD12002;
    public static final int LoginR = 0x23;
    public static final int ChannelStatus = 0x26;
    public static final int CharacterInfoRequest = 0x29;
    public static final int CharacterInfoRequestR = 0x2A;
    public static final int CreateCharacter = 0x2B;
    public static final int CreateCharacterR = 0x2C;
    public static final int DeleteCharacterRequest = 0x2D;
    public static final int DeleteCharacterRequestR = 0x2E;
    public static final int ChannelInfoRequest = 0x2F;
    public static final int ChannelInfoRequestR = 0x30;
    public static final int DeleteCharacter = 0x35;
    public static final int DeleteCharacterR = 0x36;
    public static final int RecoverCharacter = 0x37;
    public static final int RecoverCharacterR = 0x38;
    public static final int NameCheck = 0x39;
    public static final int NameCheckR = 0x3A;
    public static final int PetInfoRequest = 0x3B;
    public static final int PetInfoRequestR = 0x3C;
    public static final int CreatePet = 0x3D;
    public static final int CreatePetR = 0x3E;
    public static final int DeletePetRequest = 0x3F;
    public static final int DeletePetRequestR = 0x40;
    public static final int DeletePet = 0x41;
    public static final int DeletePetR = 0x42;
    public static final int RecoverPet = 0x43;
    public static final int RecoverPetR = 0x44;
    public static final int CreatePartner = 0x45;
    public static final int CreatePartnerR = 0x46;
    public static final int AccountInfoRequest = 0x47;
    public static final int AccountInfoRequestR = 0x48;
    public static final int AcceptGift = 0x49;
    public static final int AcceptGiftR = 0x4A;
    public static final int RefuseGift = 0x4B;
    public static final int RefuseGiftR = 0x4C;
    public static final int DisconnectInform = 0x4D;
    public static final int PetCreationOptionsRequest = 0x50;
    public static final int PetCreationOptionsRequestR = 0x51;
    public static final int PartnerCreationOptionsRequest = 0x55;
    public static final int PartnerCreationOptionsRequestR = 0x56;
    public static final int LoginUnk = 0x5A;
    // Sent on login
    public static final int LoginUnkR = 0x5B;
    // ^ Response, only known parameter: 0 byte.
    public static final int TradeCard = 0x5C;
    public static final int TradeCardR = 0x5D;
    // Channel Server
    // ------------------------------------------------------------------
    public static final int ChannelLogin = 0x4E22;
    public static final int ChannelLoginR = 0x4E23;
    public static final int DisconnectRequest = 0x4E24;
    public static final int DisconnectRequestR = 0x4E25;
    public static final int RequestClientDisconnect = 0x4E26;
    public static final int Disappear = 0x4E2A;
    public static final int SwitchChannel = 0x4E32;
    public static final int SwitchChannelR = 0x4E33;
    public static final int GetChannelList = 0x4E34;
    public static final int GetChannelListR = 0x4E35;
    public static final int WarpUnk1 = 0x4E39;
    public static final int RequestRebirth = 0x4E84;
    public static final int RequestRebirthR = 0x4E85;
    public static final int PonsUpdate = 0x4E8F;
    // b:2, i:amount, sent on login
    public static final int ChannelCharacterInfoRequest = 0x5208;
    public static final int ChannelCharacterInfoRequestR = 0x5209;
    public static final int EntityAppears = 0x520C;
    public static final int EntityDisappears = 0x520D;
    public static final int CreatureBodyUpdate = 0x520E;
    public static final int ItemAppears = 0x5211;
    public static final int ItemDisappears = 0x5212;
    public static final int AssignSittingProp = 0x5215;
    public static final int Chat = 0x526C;
    public static final int Notice = 0x526D;
    public static final int WarpUnk2 = 0x526E;
    public static final int MsgBox = 0x526F;
    public static final int AcquireInfo = 0x5271;
    public static final int WhisperChat = 0x5273;
    public static final int BeginnerChat = 0x5275;
    public static final int AcquireInfo2 = 0x5278;
    // ?
    public static final int VisualChat = 0x527A;
    public static final int PropAppears = 0x52D0;
    public static final int PropDisappears = 0x52D1;
    public static final int PropUpdate = 0x52D2;
    // Doors, MGs?
    public static final int EntitiesAppear = 0x5334;
    public static final int EntitiesDisappear = 0x5335;
    public static final int RemoveDeathScreen = 0x53FD;
    public static final int IsNowDead = 0x53FC;
    public static final int Revive = 0x53FE;
    public static final int Revived = 0x53FF;
    public static final int DeadMenu = 0x5401;
    public static final int DeadMenuR = 0x5402;
    public static final int DeadFeather = 0x5403;
    public static final int UseGesture = 0x540E;
    public static final int UseGestureR = 0x540F;
    public static final int NpcTalkStart = 0x55F0;
    public static final int NpcTalkStartR = 0x55F1;
    public static final int NpcTalkEnd = 0x55F2;
    public static final int NpcTalkEndR = 0x55F3;
    public static final int NpcTalkEgo = 0x55F4;
    public static final int NpcTalkEgoR = 0x55F5;
    public static final int NpcTalkPartner = 0x55F8;
    public static final int NpcTalkPartnerR = 0x55F9;
    public static final int ItemBlessed = 0x5BD8;
    public static final int ItemMove = 0x59D8;
    public static final int ItemMoveR = 0x59D9;
    public static final int ItemPickUp = 0x59DA;
    public static final int ItemPickUpR = 0x59DB;
    public static final int ItemDrop = 0x59DC;
    public static final int ItemDropR = 0x59DD;
    public static final int ItemMoveInfo = 0x59DE;
    public static final int ItemSwitchInfo = 0x59DF;
    public static final int ItemNew = 0x59E0;
    public static final int ItemRemove = 0x59E1;
    public static final int ItemDestroy = 0x59E2;
    public static final int ItemDestroyR = 0x59E4;
    public static final int EquipmentChanged = 0x59E6;
    public static final int EquipmentMoved = 0x59E7;
    public static final int ItemSplit = 0x59E8;
    public static final int ItemSplitR = 0x59E9;
    public static final int ItemAmount = 0x59EA;
    public static final int UseItem = 0x59EB;
    public static final int UseItemR = 0x59EC;
    public static final int GiftItem = 0x59EF;
    public static final int GiftItemR = 0x59F0;
    public static final int UnequipBag = 0x59F4;
    public static final int UnequipBagR = 0x59F5;
    // [180300, NA166 (18.09.2013)] 2 new ops
    //public const int ? = 0x59F8; // Request for that --v ?
    //public const int ? = 0x59F9; // "Unable to receive [something]."
    // Does this actually have to do something with Npcs? Sent by the
    // server when selecting "@end", before the actual NpcTalkEnd
    public static final int NpcTalkSelectEnd = 0x59FB;
    public static final int SwitchSet = 0x5BCD;
    public static final int SwitchSetR = 0x5BCE;
    public static final int UpdateWeaponSet = 0x5BCF;
    public static final int ItemStateChange = 0x5BD0;
    public static final int ItemStateChangeR = 0x5BD1;
    public static final int ItemUpdate = 0x5BD4;
    public static final int ItemDurabilityUpdate = 0x5BD5;
    public static final int ItemMaxDurabilityUpdate = 0x5BD6;
    public static final int ItemStateChanged = 0x5BD9;
    public static final int ItemExpUpdate = 0x5BDA;
    public static final int ItemRepairResult = 0x5BDB;
    public static final int ItemUpgradeResult = 0x5BDC;
    public static final int ViewEquipment = 0x5BDF;
    public static final int ViewEquipmentR = 0x5BE0;
    public static final int OptionSet = 0x5BE7;
    public static final int OptionSetR = 0x5BE8;
    public static final int AddKeyword = 0x5DC1;
    public static final int RemoveKeyword = 0x5DC3;
    public static final int NpcTalkKeyword = 0x5DC4;
    public static final int NpcTalkKeywordR = 0x5DC5;
    public static final int AddObserverRequest = 0x61A8;
    public static final int SetLocation = 0x6594;
    public static final int TurnTo = 0x6596;
    public static final int EnterRegion = 0x6597;
    public static final int EnterRegionRequest = 0x6598;
    public static final int WarpRegion = 0x6599;
    // on warp
    public static final int ForceRunTo = 0x659A;
    public static final int ForceWalkTo = 0x659B;
    public static final int EnterRegionRequestR = 0x659C;
    // on login
    public static final int UrlUpdateChronicle = 0x65A2;
    public static final int UrlUpdateAdvertise = 0x65A3;
    public static final int UrlUpdateGuestbook = 0x65A4;
    public static final int UrlUpdatePvp = 0x65A5;
    public static final int UrlUpdateDungeonBoard = 0x65A6;
    public static final int TakeOff = 0x65A8;
    public static final int TakingOff = 0x65A9;
    public static final int TakeOffR = 0x65AA;
    public static final int FlyTo = 0x65AE;
    public static final int FlyingTo = 0x65AF;
    public static final int Land = 0x65AB;
    public static final int Landing = 0x65AC;
    public static final int CanLand = 0x65AD;
    public static final int SawItemNotification = 0x65D7;
    // [190100, NA200 (2015-01-15)]
    public static final int SkillInfo = 0x6979;
    public static final int SkillTrainingUp = 0x697C;
    public static final int SkillAdvance = 0x697E;
    public static final int SkillRankUp = 0x697F;
    // Response to Advance
    public static final int SkillPrepare = 0x6982;
    public static final int SkillReady = 0x6983;
    public static final int SkillUse = 0x6986;
    public static final int SkillComplete = 0x6987;
    public static final int SkillCompleteUnk = 0x6988;
    // used in gathering fail
    public static final int SkillCancel = 0x6989;
    public static final int SkillStart = 0x698A;
    public static final int SkillStop = 0x698B;
    public static final int SkillPrepareSilentCancel = 0x698C;
    public static final int SkillUseSilentCancel = 0x698D;
    //public const int ? = 0x698E; // no parameters, found after a Complete/Cancel with one of the special horses
    public static final int SkillStartSilentCancel = 0x698F;
    public static final int SkillStopSilentCancel = 0x6990;
    public static final int SkillStackSet = 0x6991;
    public static final int SkillStackUpdate = 0x6992;
    public static final int UseMotion = 0x6D62;
    public static final int PlayAnimation = 0x6D63;
    // s:data/.../anim/..., 1:0, 2:0, 1:0
    public static final int CancelMotion = 0x6D65;
    public static final int MotionCancel2 = 0x6D66;
    // Delayed?
    public static final int LevelUp = 0x6D69;
    public static final int RankUp = 0x6D6A;
    public static final int SitDown = 0x6D6C;
    public static final int StandUp = 0x6D6D;
    public static final int ArenaHideOn = 0x6D6F;
    public static final int ArenaHideOff = 0x6D70;
    public static final int ChangeStanceRequest = 0x6E28;
    public static final int ChangeStanceRequestR = 0x6E29;
    public static final int ChangeStance = 0x6E2A;
    public static final int RiseFromTheDead = 0x701D;
    public static final int CharacterLock = 0x701E;
    public static final int CharacterUnlock = 0x701F;
    public static final int PlayDead = 0x7021;
    public static final int OpenUmbrella = 0x7025;
    public static final int CloseUmbrella = 0x7026;
    public static final int SpreadWingsOn = 0x702E;
    public static final int SpreadWingsOff = 0x702F;
    public static final int NpcShopBuyItem = 0x7150;
    public static final int NpcShopBuyItemR = 0x7151;
    public static final int NpcShopSellItem = 0x7152;
    public static final int NpcShopSellItemR = 0x7153;
    public static final int ClearNpcShop = 0x7158;
    // Empties tabs
    public static final int AddToNpcShop = 0x7159;
    // Adds items while shop is open, works like open
    public static final int OpenNpcShop = 0x715E;
    public static final int RequestBankTabs = 0x7211;
    public static final int OpenBank = 0x7212;
    public static final int CloseBank = 0x7215;
    public static final int CloseBankR = 0x7216;
    public static final int BankWithdrawItem = 0x7217;
    public static final int BankWithdrawItemR = 0x7218;
    public static final int BankDepositItem = 0x7219;
    public static final int BankDepositItemR = 0x721A;
    public static final int BankDepositGold = 0x721B;
    public static final int BankDepositGoldR = 0x721C;
    public static final int BankWithdrawGold = 0x721D;
    public static final int BankWithdrawGoldR = 0x721E;
    public static final int BankUpdateGold = 0x721F;
    public static final int BankAddItem = 0x7220;
    public static final int BankRemoveItem = 0x7221;
    public static final int OpenMail = 0x7242;
    public static final int CloseMail = 0x7243;
    public static final int ConfirmMailRecipent = 0x7244;
    public static final int ConfirmMailRecipentR = 0x7245;
    public static final int SendMail = 0x7246;
    public static final int SendMailR = 0x7247;
    public static final int GetMails = 0x7248;
    public static final int GetMailsR = 0x7249;
    public static final int MarkMailRead = 0x724A;
    public static final int MarkMailReadR = 0x724B;
    public static final int RecieveMailItem = 0x724C;
    public static final int ReceiveMailItemR = 0x724D;
    public static final int ReturnMail = 0x724E;
    public static final int ReturnMailR = 0x724F;
    public static final int DeleteMail = 0x7250;
    public static final int DeleteMailR = 0x7251;
    public static final int RecallMail = 0x7252;
    public static final int RecallMailR = 0x7253;
    public static final int UnreadMailCount = 0x7255;
    public static final int StatUpdatePrivate = 0x7530;
    public static final int StatUpdatePublic = 0x7532;
    public static final int CombatTargetUpdate = 0x791A;
    // ?
    public static final int CombatSetAim = 0x791D;
    public static final int CombatSetAimR = 0x791E;
    public static final int SetCombatTarget = 0x7920;
    public static final int SetFinisher = 0x7921;
    public static final int SetFinisher2 = 0x7922;
    public static final int CombatAction = 0x7924;
    public static final int CombatActionEnd = 0x7925;
    public static final int CombatActionPack = 0x7926;
    public static final int CombatUsedSkill = 0x7927;
    public static final int CombatAttackR = 0x7D01;
    public static final int EventInform = 0x88B8;
    public static final int NewQuest = 0x8CA0;
    public static final int QuestClear = 0x8CA1;
    public static final int QuestUpdate = 0x8CA2;
    public static final int CompleteQuest = 0x8CA3;
    public static final int CompleteQuestR = 0x8CA4;
    public static final int GiveUpQuest = 0x8CA5;
    public static final int GiveUpQuestR = 0x8CA6;
    public static final int QuestStartPtj = 0x8D68;
    public static final int QuestEndPtj = 0x8D69;
    public static final int QuestUpdatePtj = 0x8D6A;
    public static final int PartyCreate = 0x8E94;
    public static final int PartyCreateR = 0x8E95;
    public static final int PartyCreateUpdate = 0x8E96;
    public static final int PartyJoin = 0x8E97;
    public static final int PartyJoinR = 0x8E98;
    public static final int PartyJoinUpdate = 0x8E99;
    public static final int PartyLeave = 0x8E9A;
    public static final int PartyLeaveR = 0x8E9B;
    public static final int PartyLeaveUpdate = 0x8E9C;
    public static final int PartyRemove = 0x8E9D;
    public static final int PartyRemoveR = 0x8E9E;
    public static final int PartyRemoved = 0x8E9F;
    public static final int PartyChangeSetting = 0x8EA0;
    public static final int PartyChangeSettingR = 0x8EA1;
    public static final int PartySettingUpdate = 0x8EA2;
    public static final int PartyChangePassword = 0x8EA3;
    public static final int PartyChangePasswordR = 0x8EA4;
    public static final int PartyChangeLeader = 0x8EA5;
    public static final int PartyChangeLeaderR = 0x8EA6;
    public static final int PartyChangeLeaderUpdate = 0x8EA7;
    public static final int PartyWantedShow = 0x8EA9;
    public static final int PartyWantedShowR = 0x8EAA;
    public static final int PartyWantedOpened = 0x8EAB;
    public static final int PartyWantedHide = 0x8EAC;
    public static final int PartyWantedHideR = 0x8EAD;
    public static final int PartyWantedClosed = 0x8EAE;
    public static final int PartyChangeFinish = 0x8EB5;
    public static final int PartyChangeFinishR = 0x8EB6;
    public static final int PartyFinishUpdate = 0x8EB7;
    public static final int PartyChangeExp = 0x8EB8;
    public static final int PartyChangeExpR = 0x8EB9;
    public static final int PartyExpUpdate = 0x8EBA;
    public static final int GuildInfoNoGuild = 0x8EFB;
    public static final int OpenGuildPanel = 0x8EFC;
    public static final int GuildInfo = 0x8EFD;
    public static final int GuildApply = 0x8EFF;
    public static final int GuildApplyR = 0x8F00;
    public static final int GuildMembershipChanged = 0x8F01;
    public static final int GuildstoneLocation = 0x8F02;
    public static final int ConvertGp = 0x8F03;
    public static final int ConvertGpR = 0x8F04;
    public static final int ConvertGpConfirm = 0x8F05;
    public static final int ConvertGpConfirmR = 0x8F06;
    public static final int GuildDonate = 0x8F07;
    public static final int GuildDonateR = 0x8F08;
    public static final int GuildMessage = 0x8F0F;
    public static final int AddTitleKnowledge = 0x8FC0;
    public static final int AddTitle = 0x8FC1;
    public static final int ChangeTitle = 0x8FC4;
    public static final int TitleUpdate = 0x8FC5;
    public static final int ChangeTitleR = 0x8FC6;
    public static final int PetRegister = 0x9024;
    public static final int PetUnregister = 0x9025;
    public static final int SummonPet = 0x902C;
    public static final int SummonPetR = 0x902D;
    public static final int UnsummonPet = 0x9031;
    public static final int UnsummonPetR = 0x9032;
    public static final int TelePet = 0x9033;
    public static final int TelePetR = 0x9034;
    public static final int PutItemIntoPetInv = 0x9035;
    public static final int PutItemIntoPetInvR = 0x9036;
    public static final int TakeItemFromPetInv = 0x9037;
    public static final int TakeItemFromPetInvR = 0x9038;
    public static final int HitProp = 0x9088;
    public static final int HitPropR = 0x9089;
    public static final int HittingProp = 0x908A;
    public static final int TouchProp = 0x908B;
    public static final int TouchPropR = 0x908C;
    public static final int PropInteraction = 0x908D;
    // Doors?
    public static final int PlaySound = 0x908F;
    // [190100, NA198 (11.12.2014)] Something added here?
    // Effect~NaoRevivalEntrance definitely shifted by 1,
    // prop hitting was still the same.
    public static final int Effect = 0x9091;
    public static final int EffectDelayed = 0x9092;
    public static final int QuestOwlComplete = 0x9094;
    public static final int QuestOwlNew = 0x9095;
    public static final int PartyWantedUpdate = 0x9096;
    public static final int PvPInformation = 0x9097;
    public static final int NaoRevivalExit = 0x9099;
    public static final int NaoRevivalEntrance = 0x909D;
    // [190100, NA198 (11.12.2014)] End of above's shift?
    public static final int DungeonInfo = 0x9470;
    public static final int ArenaRoundInfo = 0x9667;
    public static final int ArenaRoundInfoCancel = 0x9668;
    public static final int AgeUpEffect = 0x9858;
    public static final int ConditionUpdate = 0xA028;
    public static final int CollectAnimation = 0xA415;
    public static final int CollectAnimationCancel = 0xA416;
    public static final int DyePaletteReq = 0xA418;
    public static final int DyePaletteReqR = 0xA419;
    public static final int DyePickColor = 0xA41A;
    public static final int DyePickColorR = 0xA41B;
    public static final int Transformation = 0xA41C;
    public static final int PetAction = 0xA41D;
    public static final int SharpMind = 0xA41E;
    public static final int MoonGateInfoRequest = 0xA428;
    public static final int MoonGateInfoRequestR = 0xA429;
    public static final int MoonGateMap = 0xA42D;
    public static final int MoonGateUse = 0xA42E;
    public static final int MoonGateUseR = 0xA42F;
    public static final int MusicEventInform = 0xA431;
    public static final int ItemShopInfo = 0xA436;
    public static final int PartyWindowUpdate = 0xA43C;
    public static final int ContinentWarpCoolDown = 0xA43D;
    public static final int ContinentWarpCoolDownR = 0xA43E;
    public static final int PartyTypeUpdate = 0xA44B;
    public static final int OpenItemShop = 0xA44D;
    // [150000~180000] Something was removed here
    public static final int MailsRequest = 0xA897;
    public static final int MailsRequestR = 0xA898;
    public static final int SetPetAi = 0xA8A1;
    public static final int GetPetAi = 0xA8A2;
    public static final int GetPetAiR = 0xA8A3;
    public static final int WarpUnk3 = 0xA8AF;
    public static final int UmbrellaJump = 0xA8E0;
    public static final int UmbrellaJumpR = 0xA8E1;
    public static final int UmbrellaLand = 0xA8E2;
    public static final int SetBgm = 0xA910;
    public static final int UnsetBgm = 0xA911;
    public static final int EnableRoyalAlchemist = 0xA9A3;
    public static final int SosButtonRequest = 0xA9A9;
    public static final int SosButtonRequestR = 0xA9AA;
    public static final int SkillTeleport = 0xA9F0;
    // [150000~180000] Something was added? Next two ops changed.
    // [180800, NA196] Something was added? Ops 0xAAXX - 0xABXX increased by 4.
    public static final int SubsribeStabilityMeter = 0xAA21;
    public static final int StabilityMeterInit = 0xAA22;
    public static final int StabilityMeterUpdate = 0xAA23;
    public static final int HomesteadInfoRequest = 0xAA58;
    public static final int HomesteadInfoRequestR = 0xAA59;
    // [180300, NA166 (18.09.2013)] 2 new ops somewhere here, possibly the two below
    public static final int ChannelLoginUnk = 0xAA87;
    public static final int ChannelLoginUnkR = 0xAA88;
    public static final int CollectionRequest = 0xAA8B;
    public static final int CollectionRequestR = 0xAA8C;
    public static final int UnkEsc = 0xAAF3;
    public static final int GoBeautyShop = 0xAAF8;
    public static final int GoBeautyShopR = 0xAAF9;
    public static final int LeaveBeautyShop = 0xAAFA;
    public static final int LeaveBeautyShopR = 0xAAFB;
    public static final int OpenBeautyShop = 0xAAFC;
    //public const int ? = 0xAAFD;	// Buy looks?
    //public const int ? = 0xAAFE;	// Buy looks R?
    public static final int CancelBeautyShop = 0xAAFF;
    public static final int CancelBeautyShopR = 0xAB00;
    public static final int TalentInfoUpdate = 0xAB17;
    public static final int TalentTitleChange = 0xAB18;
    public static final int TalentTitleUpdate = 0xAB19;
    public static final int ShamalaTransformationUpdate = 0xAB1B;
    public static final int ShamalaTransformationUse = 0xAB1C;
    public static final int ShamalaTransformation = 0xAB1D;
    public static final int ShamalaTransformationEnd = 0xAB1E;
    public static final int ShamalaTransformationEndR = 0xAB1F;
    // [190100, NA200 (2015-01-15)]
    public static final int ItemMagnet = 0xABAB;
    public static final int NpcTalk = 0x13882;
    public static final int NpcTalkSelect = 0x13883;
    public static final int SpecialLogin = 0x15F90;
    // ?
    public static final int EnterSoulStream = 0x15F91;
    //public const int ? = 0x15F92;
    public static final int LeaveSoulStream = 0x15F93;
    public static final int LeaveSoulStreamR = 0x15F94;
    public static final int FinishedCutscene = 0x186A0;
    public static final int PlayCutscene = 0x186A6;
    public static final int CutsceneEnd = 0x186A7;
    public static final int CutsceneUnk = 0x186A8;
    public static final int Weather = 0x1ADB0;
    public static final int GmcpOpen = 0x1D589;
    public static final int GmcpClose = 0x1D58A;
    public static final int GmcpSummon = 0x1D58B;
    public static final int GmcpMoveToChar = 0x1D58C;
    public static final int GmcpWarp = 0x1D58D;
    public static final int GmcpRevive = 0x1D58E;
    public static final int GmcpInvisibility = 0x1D58F;
    public static final int GmcpInvisibilityR = 0x1D590;
    public static final int GmcpSearch = 0x1D595;
    public static final int GmcpExpel = 0x1D596;
    public static final int GmcpBan = 0x1D597;
    public static final int GmcpNpcList = 0x1D59F;
    public static final int GmcpNpcListR = 0x1D5A0;
    public static final int PetMount = 0x1FBD0;
    public static final int PetMountR = 0x1FBD1;
    public static final int PetUnmount = 0x1FBD2;
    public static final int PetUnmountR = 0x1FBD3;
    public static final int VehicleInfo = 0x1FBD4;
    public static final int Run = 0x0F213303;
    public static final int Running = 0x0F44BBA3;
    public static final int CombatAttack = 0x0FCC3231;
    public static final int Walking = 0x0FD13021;
    public static final int Walk = 0x0FF23431;
    // Internal communication
    // ------------------------------------------------------------------
    public static class Internal   
    {
        public static final int ServerIdentify = 0x42420001;
        public static final int ServerIdentifyR = 0x42420002;
        public static final int ChannelStatus = 0x42420101;
        public static final int BroadcastNotice = 0x42420201;
    }

}


// public const int MailsRequest = 0xA898;
// public const int MailsRequestR = 0xA899;