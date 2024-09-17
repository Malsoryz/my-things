object flatihan11: Tflatihan11
  Left = 0
  Top = 0
  Caption = 'Latihan 11'
  ClientHeight = 520
  ClientWidth = 326
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  Position = poDesktopCenter
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 16
    Top = 8
    Width = 296
    Height = 27
    Caption = 'Simulasi Perhitungan Tarif PLN'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -21
    Font.Name = 'Trebuchet MS'
    Font.Style = [fsBold]
    ParentFont = False
  end
  object Label2: TLabel
    Left = 24
    Top = 266
    Width = 51
    Height = 13
    Caption = 'Pemakaian'
  end
  object Label3: TLabel
    Left = 24
    Top = 313
    Width = 59
    Height = 13
    Caption = 'Biaya Beban'
  end
  object Label4: TLabel
    Left = 24
    Top = 340
    Width = 80
    Height = 13
    Caption = 'Biaya Pemakaian'
  end
  object Label5: TLabel
    Left = 24
    Top = 369
    Width = 44
    Height = 13
    Caption = 'PPJU 3%'
  end
  object Label6: TLabel
    Left = 24
    Top = 396
    Width = 36
    Height = 13
    Caption = 'Materai'
  end
  object Label7: TLabel
    Left = 24
    Top = 443
    Width = 65
    Height = 13
    Caption = 'Total Tagihan'
  end
  object golongtarif: TRadioGroup
    Left = 16
    Top = 49
    Width = 296
    Height = 97
    Caption = 'Golongan Tarif'
    Items.Strings = (
      'Sosial'
      'Rumah Tangga'
      'Bisnis')
    TabOrder = 0
    OnClick = golongtarifClick
  end
  object daya: TRadioGroup
    Left = 16
    Top = 152
    Width = 296
    Height = 97
    Caption = 'Daya'
    TabOrder = 1
  end
  object teditinput1: TEdit
    Left = 191
    Top = 263
    Width = 121
    Height = 21
    TabOrder = 2
    Text = '0'
  end
  object teditoutput1: TEdit
    Left = 191
    Top = 310
    Width = 121
    Height = 21
    TabOrder = 3
    Text = '0'
  end
  object teditoutput2: TEdit
    Left = 191
    Top = 337
    Width = 121
    Height = 21
    TabOrder = 4
    Text = '0'
  end
  object teditoutput3: TEdit
    Left = 191
    Top = 366
    Width = 121
    Height = 21
    TabOrder = 5
    Text = '0'
  end
  object teditoutput4: TEdit
    Left = 191
    Top = 393
    Width = 121
    Height = 21
    TabOrder = 6
    Text = '0'
  end
  object teditoutputhasil: TEdit
    Left = 191
    Top = 440
    Width = 121
    Height = 21
    TabOrder = 7
    Text = '0'
  end
  object btn1: TButton
    Left = 237
    Top = 487
    Width = 75
    Height = 25
    Caption = 'Hitung'
    TabOrder = 8
    OnClick = btn1Click
  end
end
