object FLatihan3: TFLatihan3
  Left = 0
  Top = 0
  BorderStyle = bsSingle
  Caption = 'Latihan5'
  ClientHeight = 401
  ClientWidth = 457
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
  object drh: TLabel
    Left = 24
    Top = 8
    Width = 240
    Height = 29
    Alignment = taCenter
    Caption = 'Daftar Riwayat Hidup'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -24
    Font.Name = 'Trebuchet MS'
    Font.Style = [fsBold]
    ParentFont = False
  end
  object nama: TLabel
    Left = 24
    Top = 59
    Width = 26
    Height = 13
    Caption = 'nama'
  end
  object ttl: TLabel
    Left = 24
    Top = 86
    Width = 104
    Height = 13
    Caption = 'Tempat, Tanggal lahir'
  end
  object Jenisk: TLabel
    Left = 24
    Top = 112
    Width = 63
    Height = 13
    Caption = 'Jenis Kelamin'
  end
  object agama: TLabel
    Left = 24
    Top = 136
    Width = 33
    Height = 13
    Caption = 'Agama'
  end
  object pekerjaan: TLabel
    Left = 24
    Top = 165
    Width = 48
    Height = 13
    Caption = 'Pekerjaan'
  end
  object alamat: TLabel
    Left = 24
    Top = 256
    Width = 33
    Height = 13
    Caption = 'Alamat'
  end
  object kodepos: TLabel
    Left = 24
    Top = 323
    Width = 41
    Height = 13
    Caption = 'Kodepos'
  end
  object Edit1: TEdit
    Left = 160
    Top = 56
    Width = 265
    Height = 21
    TabOrder = 0
    Text = 'Edit1'
  end
  object Edit2: TEdit
    Left = 160
    Top = 83
    Width = 170
    Height = 21
    TabOrder = 1
    Text = 'Edit2'
  end
  object DateTimePicker1: TDateTimePicker
    Left = 336
    Top = 83
    Width = 89
    Height = 21
    Date = 45521.000000000000000000
    Time = 0.399597766205261000
    TabOrder = 2
  end
  object pria: TRadioButton
    Left = 160
    Top = 110
    Width = 113
    Height = 17
    Caption = 'pria'
    TabOrder = 3
  end
  object wanita: TRadioButton
    Left = 271
    Top = 110
    Width = 113
    Height = 17
    Caption = 'wanita'
    TabOrder = 4
  end
  object agamabox: TComboBox
    Left = 160
    Top = 133
    Width = 145
    Height = 21
    TabOrder = 5
    Items.Strings = (
      'Islam'
      'Katolik'
      'Protestan'
      'Hindu'
      'Budha')
  end
  object kodeposn: TEdit
    Left = 160
    Top = 318
    Width = 121
    Height = 21
    MaxLength = 5
    TabOrder = 6
  end
  object save: TButton
    Left = 160
    Top = 353
    Width = 75
    Height = 25
    Caption = 'Save'
    TabOrder = 7
  end
  object reset: TButton
    Left = 241
    Top = 353
    Width = 75
    Height = 25
    Caption = 'Reset'
    TabOrder = 8
  end
  object close: TButton
    Left = 322
    Top = 353
    Width = 75
    Height = 25
    Caption = 'Close'
    TabOrder = 9
  end
  object TMemo
    Left = 160
    Top = 160
    Width = 185
    Height = 89
    TabOrder = 10
  end
  object TMemo
    Left = 160
    Top = 256
    Width = 265
    Height = 56
    TabOrder = 11
  end
end
